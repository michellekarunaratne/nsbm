/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.utility.AlertBox;
import nsbm_final.entity.DeleteTeachingLecDetails;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;


public class DeleteLecTeachingDetailsController implements Initializable {

    @FXML
    private TableView<DeleteTeachingLecDetails> tableViewTeachingLec;
    
    @FXML
    private TableColumn<DeleteTeachingLecDetails,Integer> columnEmpId;
    
    @FXML
    private TableColumn<DeleteTeachingLecDetails,String> columnSubjectCode;
    
    @FXML
    private TableColumn<DeleteTeachingLecDetails,String> columnlocation;
    
    @FXML
    private TableColumn<DeleteTeachingLecDetails,String> columnStartTime;
    
    @FXML
    private TableColumn<DeleteTeachingLecDetails,String> columnDay;
    
    @FXML
    private TableColumn<DeleteTeachingLecDetails,CheckBox> columnSelect;
    
    @FXML
    private TextField searchBox;
    
    private ObservableList<DeleteTeachingLecDetails> data;
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
        adminProfileController.setAdmin(Integer.valueOf(adminReg));
                     
        scene =new Scene(root);
        stage.setTitle("Profile");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void deleteButtonAction(ActionEvent event) throws SQLException
    {
        int rowsAffected = 0;
                
        ObservableList<DeleteTeachingLecDetails> addList= FXCollections.observableArrayList();
        
        for(DeleteTeachingLecDetails lec:data)
        {
            if(lec.getSelect().isSelected())
            {
                addList.add(lec);
            }
        }
        
        for(DeleteTeachingLecDetails lec:addList)
        {
            rowsAffected=lec.deleteLectuererTeachingDetails();
        }
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have deteleted sucessfully");
            tableViewTeachingLec.getItems().clear();
        }
    }
    
     @FXML
    private void searchRecord(KeyEvent ke) {
        
        FilteredList<DeleteTeachingLecDetails> filterData = new FilteredList<>(data, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(lecteaching -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (String.valueOf(lecteaching.getEmpId()).toLowerCase().contains(typedText)) {

                    return true;
                }
                if (lecteaching.getSubCode().toLowerCase().contains(typedText)) {

                    return true;
                }
                if (lecteaching.getLocation().toLowerCase().contains(typedText)) {
                    return true;
                }
                if (lecteaching.getStartTime().toLowerCase().contains(typedText)) {
                    return true;
                }
                 if (lecteaching.getDay().toLowerCase().contains(typedText)) {
                    return true;
                }

                return false;
            });
            
            SortedList<DeleteTeachingLecDetails> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableViewTeachingLec.comparatorProperty());
            tableViewTeachingLec.setItems(sortedList);
                       
            
        });
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void loadDetailFromDb() throws SQLException
    {
        connection = DbConnection.connect();
        data=FXCollections.observableArrayList();
        myStmt=null;
        myStmt= connection.prepareStatement("SELECT * FROM lecteaching" );
        myRs=myStmt.executeQuery();
        
        while(myRs.next())
            {
                CheckBox ch=new CheckBox("");
                data.add(new DeleteTeachingLecDetails(myRs.getInt("empId"),myRs.getString("subCode"),myRs.getString("location"),myRs.getString("start"),myRs.getString("day"),ch));
            }
        
        columnEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        columnSubjectCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        columnlocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        columnDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        columnSelect.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        
        tableViewTeachingLec.setItems(null);
        tableViewTeachingLec.setItems(data);
    }
    
    public void setAdminReg(int value)
    {
        adminReg=value;
    }
}
