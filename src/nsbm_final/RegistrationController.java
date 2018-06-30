/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.utility.AlertBox;
import nsbm_final.entity.Undergraduate;
import nsbm_final.entity.PostGraduate;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class RegistrationController implements Initializable {
   
    private Connection connection;
    private PreparedStatement myStmt_details,myStmt_post,myStmt_under;
    private ResultSet myRs_details,myRs_post,myRs_under;
    int viewRegNum;
    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private Pane pnl_personal,pnl_undergraduate,pnl_postgraduate;
    
    @FXML 
    private Button btn_personal,btn_undergraduate,btn_postgraduate,btn_proceed;
    
    @FXML 
    private ChoiceBox<String> stream;
    
    @FXML
    private ChoiceBox<String> faculty;
    
    @FXML
    private ChoiceBox<String> stuType;
    
    @FXML
    private TextField textFieldregistrationUnderGrad;
    
    @FXML
    private TextField textFieldregistrationPostGrad;
    
    @FXML
    private TextField textFieldnic;
    
    @FXML
    private TextField textFieldtelephone;
    
    @FXML
    private TextField textFieldemail;
          
    @FXML
    private TextField  textFieldcourse;
  
    @FXML 
    private TextArea textAreafirst;
    
    @FXML 
    private TextArea textArealast;
    
    @FXML
    private TextField zScore;
    
    @FXML
    private TextField rank;
    
    @FXML
    private TextField sub1;
    
    @FXML
    private TextField sub2;
    
    @FXML
    private TextField sub3;
    
    @FXML
    private TextField grade1;
    
    @FXML
    private TextField grade2;
    
    @FXML
    private TextField grade3;
    
    @FXML
    private TextField qualification;
    
    @FXML
    private TextField yearCmp;
    
    @FXML
    private TextField institute;
  
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        if(event.getSource()==btn_personal)
        {
            pnl_personal.toFront();
        }
        
        else if(event.getSource()==btn_postgraduate)
        {
            pnl_postgraduate.toFront();
        }
        else if(event.getSource()==btn_undergraduate)
        {
            pnl_undergraduate.toFront();
        }
        else if(event.getSource()==btn_proceed)
        {
            
        }
        
    }
    
     @FXML
     private void FinishButtonAction(ActionEvent event) throws SQLException, IOException
     {
       boolean sucess;
      
       if(textFieldregistrationPostGrad.getText().trim().isEmpty()||textFieldregistrationUnderGrad.getText().trim().isEmpty()||textFieldnic.getText().trim().isEmpty()||textFieldemail.getText().trim().isEmpty()||textFieldcourse.getText().trim().isEmpty())
       {
            AlertBox.display("Error","Cannot Keep Mandatory Fields Empty");
       }
       else if(textFieldregistrationUnderGrad.getText().trim().length()!=8||textFieldregistrationPostGrad.getText().trim().length()!=8)
       {
            AlertBox.display("Error","Invalid Registration ID");
       }    
       else
       {
            
            

            String stu=(String) stuType.getValue();
            if(stu=="Undergraduate")
            {
                Undergraduate student=new Undergraduate(Integer.valueOf(textFieldregistrationUnderGrad.getText().trim()),textAreafirst.getText().trim(),textArealast.getText().trim(),textFieldemail.getText().trim(),textFieldnic.getText().trim(),Integer.valueOf(textFieldtelephone.getText().trim()),(String) faculty.getValue(),textFieldcourse.getText().trim(),sub1.getText().trim(),sub2.getText().trim(),sub3.getText().trim(),grade1.getText().trim(),grade2.getText().trim(),grade3.getText().trim(),Float.valueOf(zScore.getText().trim()),Integer.valueOf(rank.getText().trim()));
                sucess=student.insertUndergraduateData();

            }
            else
            {
                PostGraduate student=new PostGraduate(Integer.valueOf(textFieldregistrationPostGrad.getText().trim()),textAreafirst.getText().trim(),textArealast.getText().trim(),textFieldemail.getText().trim(),textFieldnic.getText().trim(),Integer.valueOf(textFieldtelephone.getText().trim()),(String) faculty.getValue(),textFieldcourse.getText().trim(),qualification.getText().trim(),institute.getText().trim(),Integer.valueOf(yearCmp.getText().trim()));
                sucess=student.insertPostGraduateData();
            }
            if(sucess==false)
            {
              FXMLLoader Loader=new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));  
              stage=(Stage)((Node) event.getSource()).getScene().getWindow();
              root=(Parent) Loader.load();
              scene =new Scene(root);
              stage.setTitle("Login");
              stage.setScene(scene);
              stage.show();
            }
            else
            {
              FXMLLoader Loader=new FXMLLoader(getClass().getResource("SettingUpProfile.fxml"));  
              stage=(Stage)((Node) event.getSource()).getScene().getWindow();
              root=(Parent) Loader.load();
              
              SettingUpProfileController settingUpProfileController=Loader.getController();
              settingUpProfileController.setViewRegNum(viewRegNum);
              
              
              scene =new Scene(root);
              stage.setTitle("Profile");
              stage.setScene(scene);
              stage.show();
            }
        }
            
     }
       

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stream.setValue("Combined Math");
        stream.getItems().addAll("Combined Math","Biology","Commerce","Arts","Technical","Other");
        
        faculty.setValue("Computing");
        faculty.getItems().addAll("Computing","Business","Engineering");
        
        stuType.setValue("Undergraduate");
        stuType.getItems().addAll("Undergraduate","PostGraduate");
        
    }   
    
    public void setViewRegNum(int reg)
    {
        viewRegNum=reg;
    }
    
    public void setNewRegNum() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt_details=null;
        myRs_details=null;
        
        
        if(true)
        {
            myStmt_details=connection.prepareStatement("SELECT * FROM undergraduate WHERE regNum=(SELECT MAX(regNum) FROM undergraduate)");
            myRs_details=myStmt_details.executeQuery();
            if(myRs_details.next())
            {
                textFieldregistrationUnderGrad.setText(String.valueOf(myRs_details.getInt("regNum")+1));
            }
        }
        if(true)
        {
            myStmt_details=connection.prepareStatement("SELECT * FROM postgraduate WHERE regNum=(SELECT MAX(regNum) FROM postgraduate)");
            myRs_details=myStmt_details.executeQuery();
            if(myRs_details.next())
            {
                textFieldregistrationPostGrad.setText(String.valueOf(myRs_details.getInt("regNum")+1));
            }
        }
    }
    
   
}
