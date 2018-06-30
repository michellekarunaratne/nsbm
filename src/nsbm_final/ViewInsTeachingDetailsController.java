/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.entity.InstructorTeaching;
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
public class ViewInsTeachingDetailsController implements Initializable {

    @FXML
    private TableView<InstructorTeaching> tableViewInsTeaching;
    
    @FXML 
    private TableColumn<InstructorTeaching,String>insEmpId;
    
    @FXML 
    private TableColumn<InstructorTeaching,String>insSubCode;
    
    @FXML 
    private TableColumn<InstructorTeaching,String>insLabId;
    
    @FXML 
    private TableColumn<InstructorTeaching,String>insStartTime;
    
    @FXML 
    private TableColumn<InstructorTeaching,String>insDay;
  
    @FXML
    private TextField searchBox;
    
    private ObservableList<InstructorTeaching> data;
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
        
        FilteredList<InstructorTeaching> filterData = new FilteredList<>(data, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(insteaching -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (String.valueOf(insteaching.getEmpId()).toLowerCase().contains(typedText)) {

                    return true;
                }
                if (insteaching.getSubCode().toLowerCase().contains(typedText)) {

                    return true;
                }
                if (insteaching.getLabId().toLowerCase().contains(typedText)) {
                    return true;
                }
                if (insteaching.getStartTime().toLowerCase().contains(typedText)) {
                    return true;
                }
                
                if (insteaching.getDay().toLowerCase().contains(typedText)) {
                    return true;
                }

                return false;
            });
            SortedList<InstructorTeaching> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableViewInsTeaching.comparatorProperty());
            tableViewInsTeaching.setItems(sortedList);
                       
            
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
        
        myStmt= connection.prepareStatement("SELECT * FROM instructeach");
        myRs=myStmt.executeQuery();
        while(myRs.next())
        {
            data.add(new InstructorTeaching(String.valueOf(myRs.getInt("empId")),myRs.getString("subCode"),myRs.getString("labId"),myRs.getString("start"),myRs.getString("day")));
        }
        
        insEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        insSubCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        insLabId.setCellValueFactory(new PropertyValueFactory<>("labId"));
        insStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        insDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        
        tableViewInsTeaching.setItems(null);
        tableViewInsTeaching.setItems(data);
    }
    
    public void setAdmin(int value)
    {
        adminReg=value;
    }
    
    
}
