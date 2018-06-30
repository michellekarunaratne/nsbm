/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;


import nsbm_final.utility.AlertBox;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.sql.*;

/**
 *
 * @author Michelle
 */
public class FXMLDocumentController implements Initializable {
    
    private Connection connection;
    private PreparedStatement myStmt;
    private ResultSet myRs;
    
    
    
    @FXML
    private Button btn_login,btn_signup;
    
    @FXML
    private TextField textFeilduserName;
    
    @FXML
    private PasswordField passwordFielduserName;
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    }
    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private void handleButtonAction (ActionEvent event) throws Exception {
        
        if(event.getSource()==btn_signup)
        {    
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("Registration.fxml"));
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
            
            RegistrationController registrationController=Loader.getController();
            registrationController.setNewRegNum();
            
            scene =new Scene(root);
            stage.setTitle("Sign Up");
            stage.setScene(scene);
            stage.show();
            
        }
        else if(event.getSource()==btn_login)
        {
       
            connection = DbConnection.connect();
            myStmt=null;
            myRs=null;
            
            
           if(textFeilduserName.getText().length()==8)
           {
                myStmt= connection.prepareStatement("SELECT * FROM login WHERE loginId=? AND password =?");
            
                myStmt.setInt(1,Integer.valueOf(textFeilduserName.getText()));
                myStmt.setString(2,passwordFielduserName.getText());
            
            
                myRs=myStmt.executeQuery();
                if(myRs.next())
                {
                FXMLLoader Loader=new FXMLLoader(getClass().getResource("StudentProfile.fxml"));  
                stage=(Stage) passwordFielduserName.getScene().getWindow();
                root=(Parent) Loader.load();
               
                StudentProfileController studentProfileController=Loader.getController();
                studentProfileController.setText(textFeilduserName.getText());
              
                scene =new Scene(root);
                stage.setTitle("Profile");
                stage.setScene(scene);
                stage.show();
                }
           }
           else if(textFeilduserName.getText().length()==5)
           {
                myStmt= connection.prepareStatement("SELECT * FROM login WHERE loginId=? AND password =?");
            
                myStmt.setInt(1,Integer.valueOf(textFeilduserName.getText()));
                myStmt.setString(2,passwordFielduserName.getText());
            
            
                myRs=myStmt.executeQuery();
                if(myRs.next())
                {
                FXMLLoader Loader=new FXMLLoader(getClass().getResource("AdminProfile.fxml"));  
                stage=(Stage) passwordFielduserName.getScene().getWindow();
                root=(Parent) Loader.load();
                
                AdminProfileController adminProfileController=Loader.getController();
                adminProfileController.setAdmin(Integer.valueOf(textFeilduserName.getText()));
              
                scene =new Scene(root);
                stage.setTitle("Profile");
                stage.setScene(scene);
                stage.show();
                }
           }     
           else
           {
                AlertBox.display("Invalid Registration number or Password","Invalid Username or Password");
           }
                
        }    
    }
   
    
   
 

    
    
    
}
