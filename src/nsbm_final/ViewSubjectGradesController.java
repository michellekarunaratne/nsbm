/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.utility.AlertBox;
import nsbm_final.entity.SubGrades;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class ViewSubjectGradesController implements Initializable {
    
    @FXML
    private TableView<SubGrades> tableViewExamGrade;
    
    @FXML
    private TableColumn<SubGrades,String> columnRegistrationNumber;
    
    @FXML
    private TableColumn<SubGrades,String> columnGrade;
    
    @FXML
    private TextField textFieldSubCode,textFieldExamYear,searchBox;
    
    private ObservableList<SubGrades>data;
    private Connection connection;
    private PreparedStatement myStmt;
    private ResultSet myRs;
    int adminReg;
    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private void AdminButtonAction(ActionEvent event) throws IOException
    {
        
        FXMLLoader Loader=new FXMLLoader(getClass().getResource("AdminProfile.fxml"));  
        stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        root=(Parent) Loader.load();
        
        AdminProfileController adminProfileController=Loader.getController();
        adminProfileController.setAdmin(adminReg);
                     
        scene =new Scene(root);
        stage.setTitle("Profile");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    public void  EditAssignmentGradeRegNumCellEvent(TableColumn.CellEditEvent editevent)
    {
        SubGrades subGradeSelected= tableViewExamGrade.getSelectionModel().getSelectedItem();
        subGradeSelected.setRegNum(editevent.getNewValue().toString());
    }
    
    @FXML
    public void  EditAssignmentGradeCellEvent(TableColumn.CellEditEvent editevent)
    {
        SubGrades subGradeSelected= tableViewExamGrade.getSelectionModel().getSelectedItem();
        subGradeSelected.setGrade(editevent.getNewValue().toString());
    }
    
    @FXML
    private ObservableList LoadDataFromDb(ActionEvent event) throws SQLException
    {
        data=FXCollections.observableArrayList();
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        
        myStmt= connection.prepareStatement("SELECT * FROM exam INNER JOIN exaresult ON exam.examCode=exaresult.examCode WHERE subCode=? AND examYear=?");
        
        myStmt.setString(1,textFieldSubCode.getText());
        myStmt.setInt(2,Integer.valueOf(textFieldExamYear.getText()));
        myRs=myStmt.executeQuery();
        
        while(myRs.next())
        {
            data.add(new SubGrades(textFieldSubCode.getText(),myRs.getString("examCode"),String.valueOf(myRs.getString("regNum")),myRs.getString("grade")));
        }
        
        columnRegistrationNumber.setCellValueFactory(new PropertyValueFactory<>("regNum"));
        columnGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        
   
        tableViewExamGrade.setItems(null);
        tableViewExamGrade.setItems(data);
        
        return data;
    }
    
    @FXML
    private void searchRecord(KeyEvent ke) {
        
        FilteredList<SubGrades> filterData = new FilteredList<>(data, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(subGrade -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (subGrade.getRegNum().toLowerCase().contains(typedText)) {

                    return true;
                }
                if (subGrade.getGrade().toLowerCase().contains(typedText)) {

                    return true;
                }

                return false;
            });
            SortedList<SubGrades> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableViewExamGrade.comparatorProperty());
            tableViewExamGrade.setItems(sortedList);
                       
            
        });
    }
    
    @FXML
    private void EditExamGrades(ActionEvent event) throws SQLException
    {
     
        int rowsAffected = 0;
          
        for(SubGrades gradeDetails:data)
        {
            rowsAffected=gradeDetails.editExamSubGrade();
        }
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have edited data sucessfully");
            tableViewExamGrade.getItems().clear();
                
        }
        
    }  

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        data=FXCollections.observableArrayList();
        
        tableViewExamGrade.setEditable(true);
        columnRegistrationNumber.setCellFactory(TextFieldTableCell.forTableColumn());
        columnGrade.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    
    public void setAdmin(int value)
    {
        adminReg=value;
    }
    
}
