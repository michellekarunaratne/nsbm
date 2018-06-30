/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.utility.AlertBox;
import nsbm_final.entity.Subject;
import nsbm_final.entity.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


public class EditSubjectController implements Initializable {
    
    int regNum;
    int year;
    int semester;
    String course;
    int rowsAffected;

    @FXML
    private TableView<Subject> tableSubject;
    
    @FXML
    private TableColumn<Subject,String> columnSubjectCode;
    
    @FXML
    private TableColumn<Subject,String> columnSubjectName;
    
    @FXML
    private TableColumn<Subject,Integer> columnCredit;
    
    @FXML
    private TableColumn<Subject,Integer> columnFee;
    
    @FXML
    private TableColumn<Subject,CheckBox> columnSelect;
    
    private ObservableList<Subject> data;
    private Connection connection;
    private PreparedStatement myStmt,myStmt_enrollsub,myStmt_enroll;
    private ResultSet myRs,myRs_enrollsub;
    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private void doneButtonAction(ActionEvent event) throws SQLException
    {
        ObservableList<Subject> addList= FXCollections.observableArrayList();
        Student student;
        int fee=0;
        
        
        for(Subject sub:data)
        {
            if(sub.getSelect().isSelected())
            {
                addList.add(sub);
            }
        }

        for(Subject sub:addList)
        {
            fee+=sub.getFee();
            rowsAffected=sub.addEnrollSubjectDetails(regNum,year,semester);
        }
        if(rowsAffected!=0)
        {
            student=new Student(regNum,fee,course,convertDate());
            rowsAffected=student.addStudentEnrollDetails();
        }
      
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have enrolled sucessfully");
            tableSubject.getItems().clear();
        } 
        
    }
    @FXML
    private void enrollButtonAction(ActionEvent event) throws IOException
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
    
 
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void setData(int reg,String course)
    {
        regNum=reg;
        this.course=course;
    }
 
    public void loadDataFromDatabase(String course,int year,int reg) throws SQLException
    {
        setData(reg,course);
        connection = DbConnection.connect();
        data=FXCollections.observableArrayList();
        myStmt=null;
        myStmt= connection.prepareStatement("SELECT * FROM subject WHERE semester=? AND courseId=? AND year=?" );
        
        this.year=year;
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        
       
        if(month>=1 && month<=6)
        {
            this.semester=1;
            myStmt.setInt(1,1);
            myStmt.setString(2,course);
            myStmt.setInt(3,year);
            myRs=myStmt.executeQuery();
            while(myRs.next())
            {
                CheckBox ch=new CheckBox("");
                if(myRs.getInt("mode")==1)
                {
                    ch.setSelected(true);
                    ch.setDisable(true);
                }
                data.add(new Subject(myRs.getString("subCode"),myRs.getString("name"),myRs.getInt("credits"),myRs.getInt("fee"),ch));
            }
        }
        else
        {
            this.semester=2;
            myStmt.setInt(1,2);
            myStmt.setString(2,course);
            myRs=myStmt.executeQuery();
            while(myRs.next())
            {
                CheckBox ch=new CheckBox("");
                data.add(new Subject(myRs.getString("subCode"),myRs.getString("subName"),myRs.getInt("credit"),myRs.getInt("fee"),ch));
            }
        }
        
        columnSubjectCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        columnSubjectName.setCellValueFactory(new PropertyValueFactory<>("subName"));
        columnCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        columnFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        columnSelect.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        
        tableSubject.setItems(null);
        tableSubject.setItems(data);
    } 
    
    public String convertDate() 
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        String day=(String)dateFormat.format(date); 
        return day;
    }
}  

