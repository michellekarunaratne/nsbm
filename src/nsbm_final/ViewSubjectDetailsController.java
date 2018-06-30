/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.entity.SubjectViewStudent;
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
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class ViewSubjectDetailsController implements Initializable {

    @FXML
    private TableView<SubjectViewStudent> tableViewSubject;
    
    @FXML 
    private TableColumn<SubjectViewStudent,String> columnSubjectCode;
    
    @FXML 
    private TableColumn<SubjectViewStudent,String>columnSubjectName;
    
    @FXML 
    private TableColumn<SubjectViewStudent,String>columnSubjectCourse;
    
    @FXML 
    private TableColumn<SubjectViewStudent,String>columnSubjectCourseName;
    
    @FXML 
    private TableColumn<SubjectViewStudent,Integer>columnSubjectYear;
    
    @FXML 
    private TableColumn<SubjectViewStudent,Integer>columnSubjectSemester;
    
    @FXML 
    private TableColumn<SubjectViewStudent,Integer>columnSubjectCredits;
    
    @FXML 
    private TableColumn<SubjectViewStudent,Integer>columnSubjectFee;
    
    @FXML
    private TextField searchBox;
    
     
    private ObservableList<SubjectViewStudent> data;
    private Connection connection;
    private PreparedStatement myStmt;
    private ResultSet myRs;
    int adminReg;
    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private void adminButtonAction(ActionEvent event) throws IOException
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
    private void searchRecord(KeyEvent ke) {
        
        FilteredList<SubjectViewStudent> filterData = new FilteredList<>(data, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(viewSub -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (viewSub.getSubCode().toLowerCase().contains(typedText)) {

                    return true;
                }
                if (viewSub.getSubName().toLowerCase().contains(typedText)) {

                    return true;
                }
                if (viewSub.getCourse().toLowerCase().contains(typedText)) {
                    return true;
                }
                if (viewSub.getCourseName().toLowerCase().contains(typedText)) {
                    return true;
                }
                
                if (String.valueOf(viewSub.getYear()).toLowerCase().contains(typedText)) {
                    return true;
                }
                
                if (String.valueOf(viewSub.getSemester()).toLowerCase().contains(typedText)) {
                    return true;
                }
                
                if (String.valueOf(viewSub.getCredit()).toLowerCase().contains(typedText)) {
                    return true;
                }
                 
                if (String.valueOf(viewSub.getFee()).toLowerCase().contains(typedText)) {
                    return true;
                }

                return false;
            });
            SortedList<SubjectViewStudent> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableViewSubject.comparatorProperty());
            tableViewSubject.setItems(sortedList);
                       
            
        });
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

     public void LoadDataFromDb() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        data=FXCollections.observableArrayList();
        
        myStmt= connection.prepareStatement("SELECT * FROM course INNER JOIN subject ON course.courseId=subject.courseId");
        myRs=myStmt.executeQuery();
        while(myRs.next())
        {
            data.add(new SubjectViewStudent(String.valueOf(myRs.getString("subCode")),myRs.getString("name"),myRs.getString("subject.courseId"),myRs.getString("course.courseName"),myRs.getInt("Year"),myRs.getInt("semester"),myRs.getInt("credits"),myRs.getInt("fee")));
        }
        
        columnSubjectCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        columnSubjectName.setCellValueFactory(new PropertyValueFactory<>("subName"));
        columnSubjectCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        columnSubjectCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        columnSubjectYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnSubjectSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        columnSubjectCredits.setCellValueFactory(new PropertyValueFactory<>("credit"));
        columnSubjectFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
                
        tableViewSubject.setItems(null);
        tableViewSubject.setItems(data);
    }
    
    public void setAdmin(int value)
    {
        adminReg=value;
    }
    
}
