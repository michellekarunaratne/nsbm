/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.utility.AlertBox;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class SettingUpProfileController implements Initializable {
    
    private Connection connection;
    private PreparedStatement myStmt;
    private ResultSet myRs;
    int viewRegNum;
    
    Stage stage;
    Parent root;
    Scene scene;

    @FXML
    private TextField userId;
    
    @FXML
    private TextField password,confirmPassword;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException
    {
        if(password.getText().trim()!=confirmPassword.getText().trim())
        {
            AlertBox.display("Error","Password Mismatch");
        }
        else
        {
            connection = DbConnection.connect();
            myStmt=null;
            myRs=null;

            int rowsAffected;

            myStmt= connection.prepareStatement("INSERT INTO login (loginId,password) VALUES(?,?)");

            myStmt.setInt(1,Integer.valueOf(userId.getText()));
            myStmt.setString(2,password.getText().trim());

            rowsAffected =myStmt.executeUpdate();

            if(rowsAffected>0)
            {
               if(checkType(viewRegNum))
               {
                    AlertBox.display("Sucessfull", "Login Has Been Created");
                    FXMLLoader Loader=new FXMLLoader(getClass().getResource("StudentProfile.fxml"));  
                    stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                    root=(Parent) Loader.load();

                    StudentProfileController studentProfileController=Loader.getController();
                    studentProfileController.setText(userId.getText());
                    studentProfileController.displayData(Integer.valueOf(userId.getText()));


                    scene =new Scene(root);
                    stage.setTitle("Profile");
                    stage.setScene(scene);
                    stage.show();
               }
               else
               {
                    AlertBox.display("Sucessfull", "Login Has Been Created");
                    FXMLLoader Loader=new FXMLLoader(getClass().getResource("AdminProfile.fxml"));  
                    stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                    root=(Parent) Loader.load();

                    scene =new Scene(root);
                    stage.setTitle("Profile");
                    stage.setScene(scene);
                    stage.show();
               }
            }
            else
            {
                AlertBox.display("Error","Data Cannot Be Inserted");
                stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                root=FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                scene =new Scene(root);
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();
            }
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setViewRegNum(int reg)
    {
        viewRegNum=reg;
    }
    
    public boolean checkType(int reg)
    {
        String num=String.valueOf(reg);
        if(num.length()==8)
            return true;
        else
            return false;
    }
    
}
