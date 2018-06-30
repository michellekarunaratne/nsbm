/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.utility.AlertBox;
import nsbm_final.entity.Undergraduate;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class Undergrad_detailsController implements Initializable {
    
    private Connection connection;
    private PreparedStatement myStmt;
    private ResultSet myRs;
    int regNum;
    int rowsAffected;
    String type;
    int viewRegNum;
    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private Label zScore,rank,subject1,subject2,subject3,grade1,grade2,grade3;
    
    @FXML
    private void profileButtonAction(ActionEvent event) throws IOException, SQLException
    {
        
        if(viewRegNum!=0)
        {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("AdminProfile.fxml"));  
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
            
            AdminProfileController adminProfileController=Loader.getController();
            adminProfileController.displayData(viewRegNum);
                  
            scene =new Scene(root);
            stage.setTitle("Profile");
            stage.setScene(scene);
            stage.show();


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
    
    public void setInt(int reg)
    {
        regNum=reg;
        
    }
    
    public void setViewReg(int reg)
    {
        viewRegNum=reg; //the person who is logged in (admin /student) is looking
        
    }
             
    public void displayData(int reg) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        Undergraduate undergraduate=null;
        
        myStmt= connection.prepareStatement("SELECT * FROM undergraduate WHERE regNum = ?");
        myStmt.setInt(1,reg);
        myRs=myStmt.executeQuery();
        
        if(myRs.next())
        {
            undergraduate=new Undergraduate(myRs.getString("subject1"),myRs.getString("subject2"),myRs.getString("subject3"),myRs.getString("grade1"),myRs.getString("grade2"),myRs.getString("grade3"),myRs.getInt("rank"),myRs.getFloat("zScore"));
     
            rank.setText(String.valueOf(undergraduate.getRank()));
            zScore.setText(String.valueOf(undergraduate.getzScore()));
            subject1.setText(undergraduate.getSubject1());
            subject2.setText(undergraduate.getSubject2());
            subject3.setText(undergraduate.getSubject3());
            grade1.setText(undergraduate.getGrade1());
            grade2.setText(undergraduate.getGrade2());
            grade3.setText(undergraduate.getGrade3());
        }
        else
        {
             AlertBox.display("Error","Invalid Registration Number");
             
        }
    }
    
}
