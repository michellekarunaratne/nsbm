/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.utility.AlertBox;
import nsbm_final.entity.Subject;
import nsbm_final.entity.Student;
import nsbm_final.entity.ExamGradeStudent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class StudentProfileController implements Initializable {
    
    private Connection connection;
    private PreparedStatement myStmt_profile,myStmt_enroll,myStmt_result;
    private ResultSet myRs_profile,myRs_enroll,myRs_result;
    int regNum;
    String courseId;
    int yearOfStudent;
    int semesterOfStudent;
    Student student = null;
   

    @FXML
    private Pane pnl_profile,pnl_enroll,pnl_result;
    
    @FXML 
    private Button btn_profile,btn_enroll,btn_result,btn_edit,btn_view,btn_editSubject;
    
    @FXML
    private Label registration,name,faculty,email,telephone;
    
    @FXML 
    private ChoiceBox<String> year;
    
    @FXML
    private ChoiceBox<String> viewYear;
    
    @FXML
    private ChoiceBox<String> viewExamSemester;
    
    @FXML
    private ChoiceBox<String> viewExamYear;
    
    @FXML
    private TableView<Subject> tableSubject;
   
    @FXML
    private TableView<ExamGradeStudent> tableGrade;
    
    @FXML
    private TableColumn<ExamGradeStudent,String> columnExamSubjectCode;
    
    @FXML
    private TableColumn<ExamGradeStudent,Integer> columnExamSemester;
    
    @FXML
    private TableColumn<ExamGradeStudent,Integer> columnExamYear;
    
    @FXML
    private TableColumn<ExamGradeStudent,String> columnExamGrade;
    
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
    private ObservableList<ExamGradeStudent> dataGrade;
    
  
    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private void logOutButton(ActionEvent event) throws IOException
    {
        FXMLLoader Loader=new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));  
        stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        root=(Parent) Loader.load();
        
        scene =new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    
    
    @FXML
    private void deleteButtonAction(ActionEvent event) throws SQLException
    {
        ObservableList<Subject> deleteList= FXCollections.observableArrayList();
        connection = DbConnection.connect();
        myStmt_profile=null;
        myRs_profile=null;
        int rowsAffected;
        
        myStmt_profile= connection.prepareStatement("DELETE FROM enroll WHERE regNum=?");
        myStmt_profile.setInt(1,regNum);
        rowsAffected=myStmt_profile.executeUpdate();
        
        for(Subject sub:data)
        {
            myStmt_profile= connection.prepareStatement("DELETE FROM enrollsub WHERE regNum=? and subCode=?");
            if(sub.getSelect().isSelected())
            {
                myStmt_profile.setInt(1,regNum);
                myStmt_profile.setString(2,sub.getSubCode());
                rowsAffected=myStmt_profile.executeUpdate();
                deleteList.add(sub);
            }
        }
        data.removeAll(deleteList);
        
    }
     @FXML
    private void displayExamGradeButton(ActionEvent event) throws SQLException
    {
         yearOfStudent=Integer.valueOf(viewExamYear.getValue());
         semesterOfStudent=Integer.valueOf(viewExamSemester.getValue());
         displayExamGrade(yearOfStudent,semesterOfStudent);
    }
    
    @FXML
    private void viewSubjectButton(ActionEvent event) throws SQLException
    {
         yearOfStudent=Integer.valueOf(viewYear.getValue());
         displaySubjectData(yearOfStudent);
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException, ParseException
    {
        if(event.getSource()==btn_profile)
        {
            pnl_profile.toFront();
        }
        else if(event.getSource()==btn_result)
        {
            pnl_result.toFront();
        }
        else if(event.getSource()==btn_enroll)
        {
            pnl_enroll.toFront();
        }
        else if(event.getSource()==btn_edit)
        {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("EditStudentProfile.fxml"));  
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
              
            EditStudentProfileController editStudentProfileController=Loader.getController();
            editStudentProfileController.setInt(regNum);
            editStudentProfileController.displayData(regNum);
              
            scene =new Scene(root);
            stage.setTitle("Edit Profile");
            stage.setScene(scene);
            stage.show();
        }
        else if(event.getSource()==btn_view)
        {
            if(checkType(regNum))
            {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("Undergrad_details.fxml"));  
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
              
            Undergrad_detailsController undergrad_detailsController=Loader.getController();
            undergrad_detailsController.setInt(regNum);
            undergrad_detailsController.displayData(regNum);
              
            scene =new Scene(root);
            stage.setTitle("Profile");
            stage.setScene(scene);
            stage.show();
            }
            else
            {
                FXMLLoader Loader=new FXMLLoader(getClass().getResource("PostGradDetails.fxml"));  
                stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                root=(Parent) Loader.load();
              
                PostGradDetailsController postGradDetailsController=Loader.getController();
                postGradDetailsController.setInt(regNum);
                postGradDetailsController.displayData(regNum);
              
                scene =new Scene(root);
                stage.setTitle("Profile");
                stage.setScene(scene);
                stage.show();
            }
        }
        else if(event.getSource()==btn_editSubject)
        {
            if(isWithinRange()==true)
            {
                yearOfStudent=Integer.valueOf(year.getValue());
                FXMLLoader Loader=new FXMLLoader(getClass().getResource("EditSubject.fxml"));  
                stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                root=(Parent) Loader.load();

                EditSubjectController editSubjectController=Loader.getController();
                editSubjectController.loadDataFromDatabase(courseId,yearOfStudent,regNum);


                scene =new Scene(root);
                stage.setTitle("Profile");
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                AlertBox.display("Error","You Cannot Reselect Subjects");
            }
        }
        
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       year.setValue("1");
       year.getItems().addAll("1","2","3","4");
       
       viewYear.setValue("1");
       viewYear.getItems().addAll("1","2","3","4");
       
       viewExamYear.setValue("1");
       viewExamYear.getItems().addAll("1","2","3","4");
       
       viewExamSemester.setValue("1");
       viewExamSemester.getItems().addAll("1","2","3","4");        
    }
    
    public void setText(String reg)
    {
        registration.setText(reg);
        regNum=Integer.valueOf(reg);
        displayData(regNum);
    }
    
    public void displayData(int reg)
    {
        connection = DbConnection.connect();
        myStmt_profile=null;
        myRs_profile=null;
           
       
        try {
            myStmt_profile= connection.prepareStatement("SELECT * FROM student INNER JOIN FACULTY ON student.facId=faculty.IdFac  WHERE regNum = ?");
            
            myStmt_profile.setInt(1,regNum);
            myRs_profile=myStmt_profile.executeQuery();
            
            if(myRs_profile.next())
            {
                student=new Student(myRs_profile.getInt("regNum"),myRs_profile.getString("firstName"),myRs_profile.getString("lastName"),myRs_profile.getString("email"),myRs_profile.getString("nic"),myRs_profile.getInt("telephone"),myRs_profile.getString("facName"),myRs_profile.getString("courseId"));
                name.setText(student.getFirstName()+student.getLastName());
                faculty.setText(student.getFacId());
                email.setText(student.getEmail());
                telephone.setText(String.valueOf(student.getTelephone()));
                courseId=student.getCourseId();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void displaySubjectData(int yearDisplay) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt_profile=null;
        myRs_profile=null;
        data=FXCollections.observableArrayList();
        
        myStmt_profile= connection.prepareStatement("SELECT enrollsub.subCode,subject.name,subject.credits,subject.fee,enrollsub.year FROM enrollsub INNER JOIN subject ON enrollsub.subCode=subject.subCode WHERE regNum=? AND enrollsub.year=?");
        myStmt_profile.setInt(1,regNum);
        myStmt_profile.setInt(2,yearDisplay);
        myRs_profile=myStmt_profile.executeQuery();
        while(myRs_profile.next())
            {
                CheckBox ch=new CheckBox("");
                data.add(new Subject(myRs_profile.getString("subCode"),myRs_profile.getString("name"),myRs_profile.getInt("credits"),myRs_profile.getInt("fee"),ch));
            }
        columnSubjectCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        columnSubjectName.setCellValueFactory(new PropertyValueFactory<>("subName"));
        columnCredit.setCellValueFactory(new PropertyValueFactory<>("credit"));
        columnFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        columnSelect.setCellValueFactory(new PropertyValueFactory<>("select"));
        
        
        tableSubject.setItems(null);
        tableSubject.setItems(data);   
    }
    
    public void displayExamGrade(int yearDisplay,int semester) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt_profile=null;
        myRs_profile=null;
        dataGrade=FXCollections.observableArrayList();
        
        myStmt_profile= connection.prepareStatement("SELECT * FROM exam INNER JOIN exaresult ON exam.examCode=exaresult.examCode WHERE (exaresult.regNum=?) AND (exam.year=?) AND (exam.semester=?)");
        myStmt_profile.setInt(1,regNum);
        myStmt_profile.setInt(2,yearDisplay);
        myStmt_profile.setInt(3,semester);
        myRs_profile=myStmt_profile.executeQuery();
        
        while(myRs_profile.next())
        {
           dataGrade.add(new ExamGradeStudent(myRs_profile.getString("subCode"),myRs_profile.getString("grade"),myRs_profile.getInt("year"),myRs_profile.getInt("semester")));
        }
        
        columnExamSubjectCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        columnExamGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        columnExamYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnExamSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        
        
        
        tableGrade.setItems(null);
        tableGrade.setItems(dataGrade);   
        
        
        
    }
    
    public boolean checkType(int reg)
    {
        String num=Integer.toString(reg);
        return num.charAt(2) != '1';
            
    }
    
    boolean isWithinRange() throws SQLException, ParseException
    {
        String date = null;
        connection = DbConnection.connect();
        myStmt_profile=null;
        myRs_profile=null;
        
        myStmt_profile= connection.prepareStatement("SELECT date FROM deadline Where recordNumber=?");
        myStmt_profile.setInt(1,1);
        myRs_profile=myStmt_profile.executeQuery();
        
        if(myRs_profile.next())
        {
        date=myRs_profile.getString("date");
        }
        
        //deadline
        Date endDate=new SimpleDateFormat("dd/MM/yyyy").parse(date.trim());
        
        //current date
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
        Date currentDate = new Date();
        
        //start date
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(endDate);  
        calendar.add(Calendar.DATE, -28);
        Date startDate=calendar.getTime();
        
        
        return (!(currentDate.before(startDate) || currentDate.after(endDate)));
    }
    
   

    
    
}
