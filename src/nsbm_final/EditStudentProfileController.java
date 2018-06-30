/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.utility.AlertBox;
import nsbm_final.entity.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class EditStudentProfileController implements Initializable {
    
    private Connection connection;
    private PreparedStatement myStmt;
    private ResultSet myRs;
    int regNum;
    int rowsAffected;
    Student student=null;
    
    
    Stage stage;
    Parent root;
    Scene scene;

    @FXML
    private TextField textFieldfirstName,textFieldsecondName,textFieldemail,textFieldtelephone;
    
    @FXML
    private Button btn_done,btn_profile;
    
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException
    {
        if(event.getSource()==btn_done)
        {    
            connection = DbConnection.connect();
            myStmt=null;
            Student student_modify=null;
        
            student_modify=new Student(regNum,textFieldfirstName.getText().trim(),textFieldsecondName.getText().trim(),textFieldemail.getText().trim(),student.getNic(),Integer.valueOf(textFieldtelephone.getText().trim()),student.getFacId(),student.getCourseId());
            rowsAffected=student_modify.updateStudentDetails();
              
            if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have edited sucessfully");
                FXMLLoader Loader=new FXMLLoader(getClass().getResource("StudentProfile.fxml"));  
                stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                root=(Parent) Loader.load();
               
                StudentProfileController studentProfileController=Loader.getController();
                studentProfileController.setText(String.valueOf(regNum));
              
                scene =new Scene(root);
                stage.setTitle("Profile");
                stage.setScene(scene);
                stage.show();
            }
         
        }
        else
        {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("StudentProfile.fxml"));  
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
               
            StudentProfileController studentProfileController=Loader.getController();
            studentProfileController.setText(String.valueOf(regNum));
              
            scene =new Scene(root);
            stage.setTitle("Profile");
            stage.setScene(scene);
            stage.show();
        }
    }
    
    
     

    
         
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setInt(int num)
    {
        regNum=num;
    }
    
    public void displayData(int reg) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        
        myStmt= connection.prepareStatement("SELECT * FROM student WHERE regNum = ?");
        myStmt.setInt(1,regNum);
        myRs=myStmt.executeQuery();
        
        if(myRs.next())
        {
            student=new Student(regNum,myRs.getString("firstName").trim(),myRs.getString("lastName").trim(),myRs.getString("email").trim(),myRs.getString("nic").trim(),Integer.valueOf(myRs.getString("telephone").trim()),myRs.getString("facId").trim(),myRs.getString("courseId").trim());
            textFieldfirstName.setText(student.getFirstName());
            textFieldsecondName.setText(student.getLastName());
            textFieldemail.setText(student.getEmail());
            textFieldtelephone.setText(String.valueOf(student.getTelephone()));
        }
        
    }    
}
