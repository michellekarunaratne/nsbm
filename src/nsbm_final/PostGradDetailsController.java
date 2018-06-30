/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.utility.AlertBox;
import nsbm_final.entity.PostGraduate;
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
public class PostGradDetailsController implements Initializable {

    private Connection connection;
    private PreparedStatement myStmt;
    private ResultSet myRs;
    int regNum;
    int rowsAffected;
    int viewRegNum;
    String type;
    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private Label qualification,yearOfCompletion,institute;
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException
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
    
    public void displayData(int reg) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        PostGraduate postGraduate=null; 
        
        myStmt= connection.prepareStatement("SELECT * FROM postgraduate WHERE regNum = ?");
        myStmt.setInt(1,reg);
        myRs=myStmt.executeQuery();
        
        if(myRs.next())
        {
           postGraduate=new PostGraduate(myRs.getString("qualificationType"),myRs.getString("institue"),myRs.getInt("yearCmp"));
        
            qualification.setText(postGraduate.getQualificationType());
            yearOfCompletion.setText(String.valueOf(postGraduate.getYearCmp()));
            institute.setText(postGraduate.getInstitue());
        }
        else
        {
             AlertBox.display("Error","Invalid Registration Number");
             
        }
    }
    public void setViewReg(int reg)
    {
        viewRegNum=reg;
        
    }
    public void check(int reg)
    {
        
        String regType=Integer.toString(reg);
        if(regType.length()==8)
        {
            type="Student";
           
        }
        else
        {
            type="Admin";
            
        }
    }
}
