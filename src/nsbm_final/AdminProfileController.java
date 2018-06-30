/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import nsbm_final.utility.SendMail;
import nsbm_final.utility.AlertBox;
import nsbm_final.entity.Undergraduate;
import nsbm_final.entity.Subject;
import nsbm_final.entity.SubGrades;
import nsbm_final.entity.Student;
import nsbm_final.entity.PostGraduate;
import nsbm_final.entity.LecturerTeaching;
import nsbm_final.entity.InstructorTeaching;
import nsbm_final.entity.Faculty;
import nsbm_final.entity.ExamSubDetails;
import nsbm_final.entity.ExamGradeStudent;
import nsbm_final.entity.ExamDetails;
import nsbm_final.entity.Employee;
import nsbm_final.entity.Course;
import nsbm_final.entity.Assignment;
import nsbm_final.utility.AlertBox;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class AdminProfileController implements Initializable {

    @FXML
    private Pane pnl_student,pnl_faculty,pnl_employee,pnl_courseSubject,pnl_result;
    
    @FXML 
    private Button btn_student,btn_faculty,btn_employee,btn_courseSubject,btn_result,btn_view,btn_detail,btn_addUndergrad,btn_postGrad,btn_editView,btn_undergradView,btn_postgradView,btn_facultyView,btn_viewEmp,btn_viewLecTeaching,btn_viewCourseDetails,btn_viewSubjectDetails,btn_viewExamGradeDetails,btn_viewExamStudentGrade,btn_viewExamSubDetails,btn_viewAssignmentGrade;
    
    @FXML
    private TextField textFieldRegistration;
    
    @FXML
    private Label name,faculty,email,telephone,course,nic;
    
    @FXML
    private TextField textFieldAddRegistration,textFieldAddNic,textFieldAddEmail,textFieldAddTelephone,textFieldAddCourse;
    
    @FXML
    private TextField textFieldEditNic,textFieldEditFirst,textFieldEditSecond,textFieldEditEmail,textFieldEditTelephone,textFieldEditRegistration;
    
    @FXML
    private TextField textFieldZscore,textFieldRank,textFieldSubject1,textFieldSubject2,textFieldSubject3,textFieldGrade1,textFieldGrade2,textFieldGrade3,textFieldEditUndegradRegistration;
    
    @FXML
    private TextField textFieldEditpostgradRegistration,textFieldEditQualification,textFieldEditYearOfCmp,textFieldEditInstitute;
    
    @FXML
    private TextArea textAreaAddFirst,textAreaAddSecond;
    
    @FXML
    private TextField textFieldEditFacId,textFieldEditFacName;
   
    @FXML
    private TextField textFieldViewEmpId;
    
    @FXML
    private Label labelEmpType,labelEmpName,labelEmpAddress,labelEmpEmail,labelEmpTelephone;
    
    @FXML
    private TextField textFieldAddEmpId,textFieldAddEmpEmail,textFieldAddEmpTelephone;
    
    @FXML
    private TextArea textAreaEmpAddName,textAreaEmpAddAddress;
    
    @FXML
    private TextField textFieldCourseId,textFieldCourseName,textFieldCourseFaculty,textFieldCourseDuration,textFieldCourseType;
    
    @FXML
    private TextField textFieldSubjectCode,textFieldSubjectName,textFieldSubjectCourseId,textFieldSubjectYear,textFieldSubjectSemester,textFieldSubjectCredits,textFieldSubjectFee,textFieldSubjectMode;
    
    @FXML
    private TextField textFieldDate;
    
    @FXML
    private TextField textFieldExamReg;
    
    @FXML
    private TextField textFieldExamSubCode,textFieldExamSubExamCode;
    
    @FXML
    private TextField textfieldExamDetailExamYear,textfieldExamDetailExamStartDate,textfieldExamDetailExamFee,textfieldExamDetailExamCode;
    
    @FXML
    private TextField textFieldAssignmentGradeSubCode,textFieldAssignmentGradeAssignmentYear;
    
    @FXML
    private TextField textFieldDeleteExamGradeRegNum,textFieldDeleteExamGradeSubCode,textFieldDeleteExamCode;
    
    @FXML
    private TextField textFieldDeleteExamDetailsExamCode;
    
    @FXML
    private TextField textFieldDeleteExamSubDetailsExamCode,textFieldDeleteExamSubDetailsSubCode;
    
    @FXML
    private TextField textFieldDeleteAssignmentDetailsRegNum,textFieldDeleteAssignmentDetailsSubCode,textFieldDeleteAssignmentDetailsYear;
    
    @FXML
    private TextField textFieldSendEmailRegNum,textFieldSendEmailExamYear;
    
    
   
    @FXML
    private TableView<Faculty> tableFaculty;
    
    @FXML
    private TableColumn<Faculty,String> columnFacId;
    
    @FXML
    private TableColumn<Faculty,String> columnFacName;
    
    @FXML
    private TableView<LecturerTeaching> lecTeaching;
    
    @FXML 
    private TableColumn<LecturerTeaching,String>lecEmpId;
    
    @FXML 
    private TableColumn<LecturerTeaching,String>lecSubCode;
    
    @FXML 
    private TableColumn<LecturerTeaching,String>lecLocation;
    
    @FXML 
    private TableColumn<LecturerTeaching,String>lecStartTime;
    
    @FXML
    private TableColumn<LecturerTeaching,String>lecDay;
    
    @FXML
    private TableView<InstructorTeaching> insTeaching;
    
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
    private TableView<ExamGradeStudent> tableExamGrade;
    
    @FXML 
    private TableColumn<ExamGradeStudent,String>columnExamSubCode;
    
    @FXML 
    private TableColumn<ExamGradeStudent,Integer>columnExamYear;
    
    @FXML 
    private TableColumn<ExamGradeStudent,Integer>columnExamSemester;
    
    @FXML 
    private TableColumn<ExamGradeStudent,String>columnExamGrade;
    
    @FXML
    private TableView<SubGrades> tableExamSubGrade;
      
    @FXML 
    private TableColumn<SubGrades,String>columnExamSubRegNum;
    
    @FXML 
    private TableColumn<SubGrades,String>columnExamSubGrade;
    
    @FXML
    private TableView<ExamSubDetails> tableExamSubDetails;
    
    @FXML 
    private TableColumn<ExamSubDetails,String>columnExamSubDetailsSubCode;
    
    @FXML 
    private TableColumn<ExamSubDetails,String>columnExamSubDetailsStartingDay;
    
    @FXML
    private TableView<Assignment> tableAssignmentGrade;
    
    @FXML 
    private TableColumn<Assignment,String>columnAssignmentGradeRegNum;
    
    @FXML 
    private TableColumn<Assignment,String>columnAssignmentGradeInClass;
    
    @FXML 
    private TableColumn<Assignment,String>columnAssignmentGradeTakeHome;
    
    
    
    
    
  
    @FXML
    private ChoiceBox<String> Addfaculty;
    
    @FXML
    private ChoiceBox<String> empType;
    
    @FXML
    private ChoiceBox<String> ExamYear;
    
    @FXML
    private ChoiceBox<String> ExamSemester;
    
    @FXML
    private ChoiceBox<String> ExamDetailYear;
    
    @FXML
    private ChoiceBox<String> ExamDetailSemester;
    
    @FXML
    private ChoiceBox<String> AssignmentGradeSubjectType;
    
    @FXML
    private ChoiceBox<String> DeleteAssignmentSubjectType;        
    
    
    
    
    
    Stage stage;
    Parent root;
    Scene scene;
    
    private Connection connection;
    private PreparedStatement myStmt,myStmt_sub,myStmt_mail;
    private ResultSet myRs,myRs_sub,myRs_mail;
    int regNum;
    int adminReg;
    String empId;
    
    private ObservableList<Faculty> data;
    private ObservableList<LecturerTeaching> datalec;
    private ObservableList<InstructorTeaching> datains;
    private ObservableList<ExamGradeStudent> datagrade;
    private ObservableList<SubGrades> dataExamGrade;
    private ObservableList<ExamSubDetails> dataExamSub;
    private ObservableList<Assignment> dataAssignmentGrade;
    
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
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException
    {
        if(event.getSource()==btn_student)
        {
            pnl_student.toFront();
        }
        else if(event.getSource()==btn_faculty)
        {
           pnl_faculty.toFront();
        }
        else if(event.getSource()==btn_employee)
        {
            pnl_employee.toFront();
            connection = DbConnection.connect();
            myStmt=null;
            myRs=null;
            int rowsAffected;
        
            myStmt= connection.prepareStatement("Select * FROM employee WHERE empId= (SELECT MAX(empId) FROM employee)");
            myRs=myStmt.executeQuery();
            
            if(myRs.next())
            {
                int newEmpID=myRs.getInt("empId")+1;
                textFieldAddEmpId.setText(String.valueOf(newEmpID));     
            }
        }
        else if(event.getSource()==btn_courseSubject)
        {
            pnl_courseSubject.toFront();
        }
        else if(event.getSource()==btn_result)
        {
            pnl_result.toFront();
        }

    }
    
    
 
    //Start Of Admin's Student Student related Methods
    
    // Add New Student's Details
    @FXML
    private void AddDetailButton(ActionEvent event) throws SQLException, IOException
    {       
        FXMLLoader Loader=new FXMLLoader(getClass().getResource("Registration.fxml"));  
        stage=(Stage)((Node) event.getSource()).getScene().getWindow();
        root=(Parent) Loader.load();
        
        RegistrationController registrationController=Loader.getController();
        registrationController.setViewRegNum(adminReg);
        registrationController.setNewRegNum();
        
        scene =new Scene(root);
        stage.setTitle("Sign Up");
        stage.setScene(scene);
        stage.show();
        
    }
    
    //Delete Existing Student's Details 
    @FXML
    private void DeleteDetailButton(ActionEvent event) throws SQLException, IOException
    {
         if(textFieldRegistration.getText().isEmpty())
         {
                AlertBox.display("Error","Please Enter The Registration Number");
         }
         else
         {
            int rowsAffected;
            Student student;
            
            student=new Student(Integer.valueOf(textFieldRegistration.getText()));
            rowsAffected=student.deleteStudentDetailsAdmin();

            if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have deleted sucessfully");
                textFieldRegistration.clear();
                name.setText("");
                faculty.setText("");
                email.setText("");
                telephone.setText("");
                course.setText("");
                nic.setText("");
            }
            else
                AlertBox.display("Error","Student Not Found");
         }
    }
    
    // View exisisting Student's Personal Details
    @FXML
    private void ViewDetailButton(ActionEvent event) throws SQLException
    {
            if(textFieldRegistration.getText().isEmpty())
            {
                AlertBox.display("Error","Please Enter The Registration Number");
            }
            else
            {
                regNum=Integer.valueOf(textFieldRegistration.getText());
                displayData(regNum);
            }
        
    }
    //View exisisting Student's Udergraduate/Postgraduate Details
    @FXML
    private void ViewMoreDetailButton(ActionEvent event) throws SQLException, IOException
    {
        int Stu_regNum=Integer.valueOf(textFieldRegistration.getText());
        if(Stu_regNum==0)
        {
            AlertBox.display("Error","Please Enter The Registration Number");
        }
        else if(Stu_regNum!=0)
        {
            if(checkType(Stu_regNum))
            {
                FXMLLoader Loader=new FXMLLoader(getClass().getResource("Undergrad_details.fxml"));  
                stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                root=(Parent) Loader.load();

                Undergrad_detailsController undergrad_detailsController=Loader.getController();
                undergrad_detailsController.setInt(Stu_regNum);
                undergrad_detailsController.setViewReg(Stu_regNum);
                undergrad_detailsController.displayData(Stu_regNum);


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
                postGradDetailsController.setInt(Stu_regNum);
                postGradDetailsController.displayData(Stu_regNum);
                postGradDetailsController.setViewReg(Stu_regNum);

                scene =new Scene(root);
                stage.setTitle("Profile");
                stage.setScene(scene);
                stage.show();
            }
        }
    }
    // Edit Exisiting Student's Details view
    @FXML
    private void EditDetailViewButton(ActionEvent event) throws SQLException
    {
        if(textFieldEditRegistration.getText().isEmpty())
        {
            AlertBox.display("Error","Please Enter The Registration Number");
        }
        else
        {
            regNum=Integer.valueOf(textFieldEditRegistration.getText());
            displayEditData(regNum);
        }
    }
    
    // Edit Student's Details
    @FXML
    private void EditDetailButton(ActionEvent event) throws SQLException, IOException
    {
        if(textFieldEditRegistration.getText().isEmpty())
        {
            AlertBox.display("Error","Please Enter The Registration Number");
        }
        else
        {
            int rowsAffected;
            Student student=new Student(Integer.valueOf(textFieldEditRegistration.getText()),textFieldEditFirst.getText().trim(),textFieldEditSecond.getText().trim(),textFieldEditEmail.getText().trim(),textFieldEditNic.getText().trim(),Integer.valueOf(textFieldEditTelephone.getText().trim()));

            rowsAffected=student.updateStudentDetailsAdmin();
            if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have edited sucessfully,Continue");
            }
        }
    }
    
    //View Student's Undergraduate Details
    @FXML
    private void ViewUnderGraduateDetailsButton(ActionEvent event) throws SQLException
    {
        if(textFieldEditUndegradRegistration.getText().isEmpty())
        {
            AlertBox.display("Error","Please Enter The Registration Number");
        }
        else
        {
            regNum=Integer.valueOf(textFieldEditUndegradRegistration.getText());
            displayUndergraduateData(regNum);     
        }
    }
    
    //Edit Student's Undergraduate Details
    @FXML
    private void EditUndegradDetailButton(ActionEvent event) throws SQLException, IOException
    {
        
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        if(textFieldEditUndegradRegistration.getText().isEmpty())
        {
            AlertBox.display("Error","Please Enter The Registration Number");
        }
        else
        {
            regNum=Integer.valueOf(textFieldEditUndegradRegistration.getText());
            Undergraduate undergraduate=new  Undergraduate(regNum,textFieldSubject1.getText().trim(),textFieldSubject2.getText().trim(),textFieldSubject3.getText().trim(),textFieldGrade1.getText().trim(),textFieldGrade2.getText().trim(),textFieldGrade3.getText().trim(),Integer.valueOf(textFieldRank.getText().trim()),Float.valueOf(textFieldZscore.getText().trim()));

            rowsAffected=undergraduate.updateUndergraduateData();
            if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have edited sucessfully,Continue");
            }
        }
    }
    
    //Edit Student's Postgraduate Details View
    @FXML
    private void ViewPostGraduateDetailsButton(ActionEvent event) throws SQLException, IOException
    {
        if(textFieldEditpostgradRegistration.getText().isEmpty())
        {
            AlertBox.display("Error","Please Enter The Registration Number");
        }
        else
        {
            regNum=Integer.valueOf(textFieldEditpostgradRegistration.getText());
            displayPostgraduateData(regNum);
        }
    }
    
    // Edit Student's Postgraduate Details
    @FXML
    private void EditPostgradDetailButton(ActionEvent event) throws SQLException, IOException
    {
        int rowsAffected;
        PostGraduate postgraduate;
        
         if(textFieldEditpostgradRegistration.getText().isEmpty())
        {
            AlertBox.display("Error","Please Enter The Registration Number");
        }
        else
        {
        
            regNum=Integer.valueOf(textFieldEditpostgradRegistration.getText());
            postgraduate=new PostGraduate(regNum,textFieldEditQualification.getText().trim(),textFieldEditInstitute.getText().trim(),Integer.valueOf(textFieldEditYearOfCmp.getText().trim()));

            rowsAffected=postgraduate.updatePostGraduateData();
            if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have edited sucessfully,Continue");
            }
        }
    }
    
    // End of Admin's Student Methods
    
    
    
    
    //Start of Admin's Faculty Methods
    
    //View Faculty Data
    @FXML
    private void ViewFacultyData(ActionEvent event) throws SQLException
    {
         displayFacData();   
    }
    
    //Edit Faculty Data
    @FXML 
    private void EditFacDetailButton(ActionEvent event) throws SQLException
    {
        if(textFieldEditFacId.getText().trim().isEmpty()||textFieldEditFacName.getText().trim().isEmpty())
        {
             AlertBox.display("Error","Cannot Keep Empty Fields");
        }
        else
        {
            Faculty faculty;
            int rowsAffected;
    
            faculty=new Faculty(textFieldEditFacId.getText().trim(),textFieldEditFacName.getText().trim());
            
            rowsAffected=faculty.updateFacultyData();
            if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have edited sucessfully");
            }
        }
    }
    
    // End of Admin's Faculty Methods
    
    
 
    // Start Of Admin's Employee Methods
    //Add New Employee Details
    @FXML
    private void AddEmpDetailsButton(ActionEvent event) throws SQLException
    {
        int rowsAffected;
        Employee employee;
        
        employee=new Employee(Integer.valueOf(textFieldAddEmpId.getText().trim()),textAreaEmpAddName.getText().trim(),empType.getValue().trim(),textAreaEmpAddAddress.getText().trim(),textFieldAddEmpEmail.getText().trim(),Integer.valueOf(textFieldAddEmpTelephone.getText().trim()));
       
        rowsAffected=employee.addEmployeeDetails();
        if(rowsAffected>0)
          {
              AlertBox.display("Sucessful","You have registered sucessfully");
              textFieldAddEmpId.clear();
              textAreaEmpAddName.clear();
              textAreaEmpAddAddress.clear();
              textFieldAddEmpTelephone.clear();
              textFieldAddEmpEmail.clear();
          }
    }
    
    //View Employee Details
    @FXML
    private void ViewEmpDetailButton(ActionEvent event) throws SQLException
    {
         if(textFieldViewEmpId.getText().isEmpty())
         {
             AlertBox.display("Error","Enter Employee ID");
         }
         else
         {
             empId=textFieldViewEmpId.getText();
             displayEmpData(empId);
         }
    }
    
    //View Lecturer Teaching Details
    @FXML
    public void ViewLecTeachingDetails(ActionEvent event) throws IOException, SQLException
    {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("ViewLecTeachingDetails.fxml"));  
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
            
            ViewLecTeachingDetailsController viewLecTeachingDetailsController=Loader.getController();
            viewLecTeachingDetailsController.setAdmin(adminReg);
             viewLecTeachingDetailsController.LoadDataFromDb();
          
            
            scene =new Scene(root);
            stage.setTitle("View Details");
            stage.setScene(scene);
            stage.show();
    }
    
    //Insert Lectuerer Teaching Details
    @FXML
    private void InsertLecTeachingDetails(ActionEvent event) throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        myStmt= connection.prepareStatement("INSERT INTO lecteaching (empId,subCode,location,start,day) VALUES(?,?,?,?,?)");
        int rowsAffected = 0;
        LecturerTeaching lecturerTeaching;
        
        for(LecturerTeaching lecdetails:datalec)
        {
            rowsAffected=lecdetails.insertLecturerTeachingDetails();  
        }
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have entered data sucessfully");
            lecTeaching.getItems().clear();
                
        }
     }
    
    //Delete Lecture teaching Details
     @FXML
    private void deleteLecTeaching(ActionEvent event) throws IOException, SQLException
    {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("DeleteLecTeachingDetails.fxml"));  
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
            
            DeleteLecTeachingDetailsController deleteLecTeachingDetailsController=Loader.getController();
            deleteLecTeachingDetailsController.setAdminReg(adminReg);
            deleteLecTeachingDetailsController.loadDetailFromDb();
          
            
            scene =new Scene(root);
            stage.setTitle("View Details");
            stage.setScene(scene);
            stage.show();
    }
    
   
    //lecturer cell edit
    @FXML
    public void EditEmpIdCellEvent(CellEditEvent editevent)
    {
        LecturerTeaching lecSelected= lecTeaching.getSelectionModel().getSelectedItem();
        lecSelected.setEmpId(editevent.getNewValue().toString());
    }
    
    @FXML
    public void EditSubCodeCellEvent(CellEditEvent editevent)
    {
        LecturerTeaching lecSelected= lecTeaching.getSelectionModel().getSelectedItem();
        lecSelected.setSubCode(editevent.getNewValue().toString());
    }
    
    @FXML
    public void EditLocationCellEvent(CellEditEvent editevent)
    {
        LecturerTeaching lecSelected= lecTeaching.getSelectionModel().getSelectedItem();
        lecSelected.setLocation(editevent.getNewValue().toString());
    }
    @FXML
    public void EditStartTimeCellEvent(CellEditEvent editevent)
    {
        LecturerTeaching lecSelected= lecTeaching.getSelectionModel().getSelectedItem();
        lecSelected.setStartTime(editevent.getNewValue().toString());
    }
    @FXML
    public void EditDayCellEvent(CellEditEvent editevent)
    {
        LecturerTeaching lecSelected= lecTeaching.getSelectionModel().getSelectedItem();
        lecSelected.setDay(editevent.getNewValue().toString());
    }

    // View Instructor Teaching Details
    @FXML
    private void ViewInsTeachingDetails(ActionEvent event) throws IOException, SQLException
    {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("ViewInsTeachingDetails.fxml"));  
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
            
            ViewInsTeachingDetailsController viewInsTeachingDetailsController=Loader.getController();
            viewInsTeachingDetailsController.setAdmin(adminReg);
            viewInsTeachingDetailsController.LoadDataFromDb();
          
            
            scene =new Scene(root);
            stage.setTitle("View Details");
            stage.setScene(scene);
            stage.show();
    } 
    
    // Insert Lectuerer Teaching Details   
    @FXML
    private void InsertInsTeachingDetails(ActionEvent event) throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        myStmt= connection.prepareStatement("INSERT INTO instructeach (empId,subCode,start,labId,day) VALUES(?,?,?,?,?)");
        int rowsAffected = 0;
       
        
        for(InstructorTeaching insDetails:datains)
        {
            rowsAffected=insDetails.insertInstructorteachingDetails();
        }
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have entered data sucessfully");
            insTeaching.getItems().clear();                
        }
     }
    
    // Delete Instructor Teaching Methods 
    @FXML
    private void deleteInsTeaching(ActionEvent event) throws IOException, SQLException
    {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("DeleteInsTeachingDetails.fxml"));  
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
            
            DeleteInsTeachingDetailsController deleteInsTeachingDetailsController=Loader.getController();
            deleteInsTeachingDetailsController.setAdminReg(adminReg);
            deleteInsTeachingDetailsController.loadDetailFromDb();
          
            
            scene =new Scene(root);
            stage.setTitle("View Details");
            stage.setScene(scene);
            stage.show();
    }
    
    
    //instructor cell edit
     @FXML
    public void EditInsEmpIdCellEvent(CellEditEvent editevent)
    {
        InstructorTeaching insSelected= insTeaching.getSelectionModel().getSelectedItem();
        insSelected.setEmpId(editevent.getNewValue().toString());
    }
    
    @FXML
    public void EditInsSubCodeCellEvent(CellEditEvent editevent)
    {
        InstructorTeaching insSelected= insTeaching.getSelectionModel().getSelectedItem();
        insSelected.setSubCode(editevent.getNewValue().toString());
    }
      
    @FXML
    public void EdiInstLabIdCellEvent(CellEditEvent editevent)
    {
        InstructorTeaching insSelected= insTeaching.getSelectionModel().getSelectedItem();
        insSelected.setLabId(editevent.getNewValue().toString());
    }
    @FXML
    public void EditInsStartTimeCellEvent(CellEditEvent editevent)
    {
        InstructorTeaching insSelected= insTeaching.getSelectionModel().getSelectedItem();
        insSelected.setStartTime(editevent.getNewValue().toString());
    }
    
    @FXML
    public void EditInsDayCellEvent(CellEditEvent editevent)
    {
        InstructorTeaching insSelected= insTeaching.getSelectionModel().getSelectedItem();
        insSelected.setDay(editevent.getNewValue().toString());
    }
    
    //End Of Admin's Employee Methods
    
    
    
    // Start Of Admin's Course And Subject Methods
    
    //View Courses
    @FXML
    private void ViewCourseButton(ActionEvent event) throws SQLException
    {
        if(textFieldCourseId.getText().isEmpty())
        {
            AlertBox.display("Error","Enter Course ID");
        }
        else
        {
            String courseId=textFieldCourseId.getText();
            displayCourseData(courseId);
        }
    }
    
    //Insert Course Data
    @FXML
    private void InsertCourseDetails(ActionEvent event) throws SQLException
     {
        if(textFieldCourseId.getText().isEmpty())
        {
            AlertBox.display("Error","Enter Course ID");
        }
        else
        {
            int rowsAffected = 0;

            String facId=textFieldCourseFaculty.getText();
            String fac = null;
            switch(facId)
            {
                case("Computing"):
                    fac="Cmp";
                    break;
                case("Business"):
                    fac="Bus";
                    break;
                case("Enginerring"):
                    fac="Eng";
                    break;
            }
            Course course=new Course(textFieldCourseId.getText(),textFieldCourseName.getText(),fac,Integer.valueOf(textFieldCourseDuration.getText()),textFieldCourseType.getText());
            rowsAffected=course.insertCourseDetails();

            if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have entered data sucessfully");
                textFieldCourseId.clear();
                textFieldCourseFaculty.clear();
                textFieldCourseName.clear();
                textFieldCourseDuration.clear();
                textFieldCourseType.clear();
            }
        }
     }
    
    //Edit Course Details
    @FXML
    private void EditCourseDetails(ActionEvent event) throws SQLException
    {       
        if(textFieldCourseId.getText().isEmpty())
        {
            AlertBox.display("Error","Enter Course ID");
        }
        else
        {
            
            int rowsAffected;
           
            String facId=textFieldCourseFaculty.getText();
            String fac = null;
            switch(facId)
            {
                case("Computing"):
                    fac="Cmp";
                    break;
                case("Business"):
                    fac="Bus";
                    break;
                case("Enginerring"):
                    fac="Eng";
                    break;
            }
            Course course=new Course(textFieldCourseId.getText(),textFieldCourseName.getText(),fac,Integer.valueOf(textFieldCourseDuration.getText()),textFieldCourseType.getText());
            rowsAffected=course.editCourseDetails();
     
            if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have edited sucessfully");
            } 
        }
       
    }
    
    //Delete Course Details
    @FXML
    private void DeleteCourseDetails(ActionEvent event) throws SQLException
    {
        if(textFieldCourseId.getText().isEmpty())
        {
            AlertBox.display("Error","Enter Course ID");
        }
        else
        {
            int rowsAffected;
            Course course;
            
            course=new Course(textFieldCourseId.getText());
            rowsAffected=course.deleteCourseDetails();
            
            if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have deleted sucessfully");
                textFieldCourseId.clear();
                textFieldCourseName.clear();
                textFieldCourseFaculty.clear();
                textFieldCourseDuration.clear();
                textFieldCourseType.clear();
            } 
        }
    }
    
    //View All Courses
    @FXML
    private void ViewCourseDetails(ActionEvent event) throws IOException, SQLException
    {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("ViewCourseDetails.fxml"));  
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
            
            ViewCourseDetailsController viewCourseDetailsController=Loader.getController();
            viewCourseDetailsController.setAdmin(adminReg);
            viewCourseDetailsController.LoadDataFromDb();
          
            
            scene =new Scene(root);
            stage.setTitle("View Details");
            stage.setScene(scene);
            stage.show();
    }
    
    //End OF Courses
    
    //Add Subject Details
     @FXML
    private void AddSubjectDetails(ActionEvent event) throws SQLException
    {
        if(textFieldSubjectCode.getText().trim().isEmpty())
        {
            AlertBox.display("Error","Enter Subject Code");
        }
        else
        {
            int rowsAffected;
            Subject subject;

            subject=new Subject(textFieldSubjectCode.getText().trim(),textFieldSubjectName.getText().trim(),Integer.valueOf(textFieldSubjectCredits.getText().trim()),Integer.valueOf(textFieldSubjectFee.getText().trim()),textFieldSubjectCourseId.getText().trim(),Integer.valueOf(textFieldSubjectYear.getText().trim()),Integer.valueOf(textFieldSubjectSemester.getText().trim()),Integer.valueOf(textFieldSubjectMode.getText()));

            rowsAffected=subject.addSubjectDetails();
            if(rowsAffected>0)
              {
                  AlertBox.display("Sucessful","You Have Entered Subject Details Sucessfully");
                  textFieldSubjectCode.clear();
                  textFieldSubjectCourseId.clear();
                  textFieldSubjectName.clear();
                  textFieldSubjectYear.clear();
                  textFieldSubjectSemester.clear();
                  textFieldSubjectCredits.clear();
                  textFieldSubjectFee.clear();
              }
        }
    }
    
    //View Subject Details
    @FXML
    private void viewSubjectData() throws SQLException
    {
        if(textFieldSubjectCode.getText().trim().isEmpty())
        {
             AlertBox.display("Error","Enter Subject Code");
        }
        else
        {
            String subjectCode=textFieldSubjectCode.getText();
            displaySubjectData(subjectCode);
        }
    }
    
    //Edit Subject Details
    @FXML
    private void EditSubjectDetails(ActionEvent event) throws SQLException
    {
        if(textFieldSubjectCode.getText().trim().isEmpty())
        {
             AlertBox.display("Error","Enter Subject Code");
        }
        else
        {
            int rowsAffected;
            Subject subject;
           
            subject=new Subject(textFieldSubjectCode.getText().trim(),textFieldSubjectName.getText().trim(),Integer.valueOf(textFieldSubjectCredits.getText().trim()),Integer.valueOf(textFieldSubjectFee.getText().trim()),Integer.valueOf(textFieldSubjectYear.getText().trim()),Integer.valueOf(textFieldSubjectSemester.getText().trim()));

            rowsAffected=subject.editSubjectDetails();
            if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have edited sucessfully,Continue");
                textFieldSubjectCode.clear();
                textFieldSubjectCourseId.clear();
                textFieldSubjectName.clear();
                textFieldSubjectYear.clear();
                textFieldSubjectSemester.clear();
                textFieldSubjectCredits.clear();
                textFieldSubjectFee.clear();
            }
        }
    }
    
    // Delete Subject Details
     @FXML
    private void DeleteSubjectDetails(ActionEvent event) throws SQLException
    {
        int rowsAffected;
        Subject subject;
     
        myStmt.setString(1,textFieldSubjectCode.getText());
        
        subject=new Subject(textFieldSubjectCode.getText()); 
        rowsAffected=subject.deleteSubjectDetails();
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have deleted sucessfully");
            textFieldSubjectCode.clear();
            textFieldSubjectCourseId.clear();
            textFieldSubjectName.clear();
            textFieldSubjectYear.clear();
            textFieldSubjectSemester.clear();
            textFieldSubjectCredits.clear();
            textFieldSubjectFee.clear();
        } 
    }
    
    //View All Subject Details
    @FXML
    private void ViewSubjectDetails(ActionEvent event) throws IOException, SQLException
    {
            FXMLLoader Loader=new FXMLLoader(getClass().getResource("ViewSubjectDetails.fxml"));  
            stage=(Stage)((Node) event.getSource()).getScene().getWindow();
            root=(Parent) Loader.load();
            
            ViewSubjectDetailsController viewSubjectDetailsController=Loader.getController();
            viewSubjectDetailsController.setAdmin(adminReg);
            viewSubjectDetailsController.LoadDataFromDb();
          
            
            scene =new Scene(root);
            stage.setTitle("View Details");
            stage.setScene(scene);
            stage.show();
    }
    
    //Add Subject Reselection DeadLine
     @FXML
    private void AddDeadline(ActionEvent event) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        myStmt=connection.prepareStatement("UPDATE deadline SET date=? WHERE recordNumber=?");
        
        myStmt.setString(1,textFieldDate.getText());
        myStmt.setInt(2,1);
        
        rowsAffected=myStmt.executeUpdate();
        if(rowsAffected>0)
            {
                AlertBox.display("Sucessful","You have add/edit sucessfully");
            }  
        
    }
    // End Of Subjects
    
    //End of Admin's Course And Subject Methods
    
    
    //Start of Admin's Exam And Grade Methods
    
    //View Student's Exam Grades
    @FXML
    private void ViewStudentExamGradeButton() throws SQLException
    {
      if(textFieldExamReg.getText().isEmpty())
      {
           AlertBox.display("Erorr","Enter Register Number");
      }
      else
      {
           int examReg=Integer.valueOf(textFieldExamReg.getText());
           int year=Integer.valueOf(ExamYear.getValue());
           int semester=Integer.valueOf(ExamSemester.getValue());
           displayExamGrades(examReg,year,semester);
       }
    }
    
    //Insert Exam grades
    @FXML
    private void InsertExamGrades(ActionEvent event) throws SQLException
    {
        int rowsAffected = 0;
        SubGrades subGrades;
        
        for(SubGrades gradeDetails:dataExamGrade)
        {
              rowsAffected=gradeDetails.insertExamSubGrade();
        }
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have entered data sucessfully");
            textFieldExamSubCode.clear();
            textFieldExamSubExamCode.clear();
            tableExamSubGrade.getItems().clear();
                
        }
        
    }
    
    // View Grade Details On One Subject
    @FXML
    private void ViewSubjectExamGrades(ActionEvent event) throws SQLException, IOException
    {
        if(event.getSource()==btn_viewExamStudentGrade)
        {
                FXMLLoader Loader=new FXMLLoader(getClass().getResource("ViewSubjectGrades.fxml"));  
                stage=(Stage)((Node) event.getSource()).getScene().getWindow();
                root=(Parent) Loader.load();
              
                ViewSubjectGradesController viewSubjectGradesController=Loader.getController();
                viewSubjectGradesController.setAdmin(regNum);
                
              
                scene =new Scene(root);
                stage.setTitle("Profile");
                stage.setScene(scene);
                stage.show();
        }
    }
    
    //Exam Grade cell edit
    @FXML
    public void EditExamRegNumCellEvent(CellEditEvent editevent)
    {
        SubGrades examSelected= tableExamSubGrade.getSelectionModel().getSelectedItem();
        examSelected.setRegNum(editevent.getNewValue().toString());
    }
    
    @FXML
    public void EditExamGradeCellEvent(CellEditEvent editevent)
    {
        SubGrades examSelected= tableExamSubGrade.getSelectionModel().getSelectedItem();
        examSelected.setGrade(editevent.getNewValue().toString());
    }
    
    //View Exam and Exam Subject Details
    @FXML
    private void ViewExamDetails(ActionEvent event) throws SQLException, IOException
    {
        if(textfieldExamDetailExamYear.getText().isEmpty())
        {
             AlertBox.display("Error","Enter Exam Year");
        }
        else
        {
            displayExamSubDetails(textfieldExamDetailExamYear.getText(),ExamDetailYear.getValue(),ExamDetailSemester.getValue());
        }
    }
    
    //Insert Exam Subject Timetable
      @FXML
    private void InsertExamSubDetails(ActionEvent event) throws SQLException
    {
        int rowsAffected;
        ExamDetails examDetails;
        
        
        examDetails=new ExamDetails(textfieldExamDetailExamCode.getText().trim(),textfieldExamDetailExamStartDate.getText().trim(),Integer.valueOf(textfieldExamDetailExamFee.getText().trim()),Integer.valueOf(ExamDetailYear.getValue().trim()),Integer.valueOf(ExamDetailSemester.getValue().trim()),Integer.valueOf(textfieldExamDetailExamYear.getText().trim()));
        rowsAffected=examDetails.insertExamDetails();
        
        for(ExamSubDetails examSubDetails:dataExamSub)
        {
            rowsAffected=examSubDetails.insertExamSubDetails();
        }
        
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have entered data sucessfully");
            tableExamSubDetails.getItems().clear();                
        }
    }
    
    //Delete Exam Subject Details
    @FXML
    private void deleteExamSubjectDetails(ActionEvent event) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        ExamSubDetails examSubDetails;
        
        examSubDetails=new ExamSubDetails(textFieldDeleteExamSubDetailsExamCode.getText().trim(),textFieldDeleteExamSubDetailsSubCode.getText().trim());
        rowsAffected=examSubDetails.deleteExamSubDetails();
       
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have deleted data sucessfully");
            textFieldDeleteExamSubDetailsExamCode.clear();
            textFieldDeleteExamSubDetailsSubCode.clear();    
        }  
    }
    
    //Exam Subject Details cell edit
    @FXML
    public void EditExamSubDetailSubCodeCellEvent(CellEditEvent editevent)
    {
        ExamSubDetails examSubDetailSelected= tableExamSubDetails.getSelectionModel().getSelectedItem();
        examSubDetailSelected.setSubCode(editevent.getNewValue().toString());
    }
    
    @FXML
    public void  EditExamSubDetailStartDayCellEvent(CellEditEvent editevent)
    {
        ExamSubDetails examSubDetailSelected= tableExamSubDetails.getSelectionModel().getSelectedItem();
        examSubDetailSelected.setStartingDay(editevent.getNewValue().toString());
    }
    
    //Edit Exam Details And Exam Subject Details
    @FXML
    private void EditExamDetails(ActionEvent event) throws SQLException
    {
        int rowsAffected = 0;
        ExamDetails examDetails;
         
        examDetails=new ExamDetails(textfieldExamDetailExamCode.getText().trim(),textfieldExamDetailExamStartDate.getText().trim(),Integer.valueOf(textfieldExamDetailExamFee.getText().trim()),Integer.valueOf(ExamDetailYear.getValue().trim()),Integer.valueOf(ExamDetailSemester.getValue().trim()),Integer.valueOf(textfieldExamDetailExamYear.getText().trim()));
        rowsAffected=examDetails.editExamDetails();

        for(ExamSubDetails examSubDetails:dataExamSub)
        {
            rowsAffected=examSubDetails.editExamSubDetails();
        }
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have edited data sucessfully");
            tableExamSubDetails.getItems().clear();
        }   
    }
    
    //Delete Exam Grade Details
    @FXML
    private void deleteExamGrades(ActionEvent event) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        SubGrades subGrades;
        
        subGrades=new SubGrades(textFieldDeleteExamGradeRegNum.getText().trim(),textFieldDeleteExamGradeSubCode.getText().trim(),textFieldDeleteExamCode.getText().trim());
        rowsAffected=subGrades.deleteExamGrade();
    
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have deleted data sucessfully");
            textFieldDeleteExamGradeRegNum.clear();
            textFieldDeleteExamGradeSubCode.clear();
            textFieldDeleteExamCode.clear();
        }  
    }
    
    // Delete Exam Details
    @FXML
    private void deleteExamDetails(ActionEvent event) throws SQLException
    {
        int rowsAffected = 0;
        ExamDetails examDetails;
        
        examDetails=new ExamDetails(textFieldDeleteExamDetailsExamCode.getText().trim());
        rowsAffected=examDetails.deleteExamDetails();
        
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have deleted data sucessfully");
            textFieldDeleteExamDetailsExamCode.clear();
        }
    }
    
    //View Assignment Grades Button
    @FXML
    private void ViewAssignmentGradeButton() throws SQLException
    {
          
        if(textFieldAssignmentGradeSubCode.getText().trim().isEmpty()||textFieldAssignmentGradeAssignmentYear.getText().trim().isEmpty())
        {
            AlertBox.display("Error","Subject Code And Exam Year Should Not Be Empty");
        }
        else
        {
            displayAssignmentGrades(textFieldAssignmentGradeSubCode.getText(),AssignmentGradeSubjectType.getValue(),textFieldAssignmentGradeAssignmentYear.getText());
        }
       
    }
    
    //Insert Assignment Grades
    @FXML
    private void InsertAssignmentGrades(ActionEvent event) throws SQLException
    {
        int rowsAffected = 0;
        String type=(String)AssignmentGradeSubjectType.getValue();
        for(Assignment assignmentGradeDetails:dataAssignmentGrade)
        {
            rowsAffected= assignmentGradeDetails.insertAssignmentGrades(type);
        }
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have entered data sucessfully");
            tableAssignmentGrade.getItems().clear();        
        }   
    }
    
    //Edit Assignment Grade 
    @FXML
    private void EditAssignmentGrades(ActionEvent event) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        String type=(String) AssignmentGradeSubjectType.getValue();
        
        for(Assignment assignmentGradeDetails:dataAssignmentGrade)
        {  
            rowsAffected=assignmentGradeDetails.editAssignmentGrade(type);  
        }
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have edited data sucessfully");
            tableAssignmentGrade.getItems().clear();
                
        }   
    }
    
    //Delete Assignment 
    @FXML
    private void deleteAssignmentDetails(ActionEvent event) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        String type;
        Assignment assignment;
        
        type=(String) DeleteAssignmentSubjectType.getValue();
        assignment=new Assignment(textFieldDeleteAssignmentDetailsRegNum.getText().trim(),textFieldDeleteAssignmentDetailsSubCode.getText().trim(),textFieldDeleteAssignmentDetailsYear.getText().trim());
        rowsAffected=assignment.deleteAssignmentGrade(type);
      
        if(rowsAffected>0)
        {
            AlertBox.display("Sucessful","You have deleted data sucessfully");
            textFieldDeleteAssignmentDetailsRegNum.clear();
            textFieldDeleteAssignmentDetailsSubCode.clear();
            textFieldDeleteAssignmentDetailsYear.clear();
        }  
    }
    //Assignment Grade cell edit
    
    @FXML
    public void  EditAssignmentGradeRegNumCellEvent(CellEditEvent editevent)
    {
        Assignment assignmentDetailSelected= tableAssignmentGrade.getSelectionModel().getSelectedItem();
        assignmentDetailSelected.setRegNum(editevent.getNewValue().toString());
    }
    
    @FXML
    public void  EditAssignmentGradeInClassEvent(CellEditEvent editevent)
    {
        Assignment assignmentDetailSelected= tableAssignmentGrade.getSelectionModel().getSelectedItem();
        assignmentDetailSelected.setInClass(editevent.getNewValue().toString());
    }
    
    @FXML
    public void  EditAssignmentGradeTakeHomeEvent(CellEditEvent editevent)
    {
        Assignment assignmentDetailSelected= tableAssignmentGrade.getSelectionModel().getSelectedItem();
        assignmentDetailSelected.setTakeHome(editevent.getNewValue().toString());
    }
  
  //Send Subject Grades Through Email    
    @FXML
    private void sendEmailButton(ActionEvent event) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myStmt_mail=null;
        myRs=null;
        myRs_mail=null;
        String message=null;
        
        myStmt_mail=connection.prepareStatement("SELECT regNum FROM exaresult GROUP BY regNum");
        myRs_mail=myStmt_mail.executeQuery();
        while(myRs_mail.next())
        {
            String email = null;
            message="Registration Number:"+myRs_mail.getInt("regNum")+"\n"+"Subject Code\tGrade\n";
            myStmt=connection.prepareStatement("SELECT * FROM exaresult INNER JOIN student ON exaresult.regNum=student.regNum INNER JOIN exam ON exam.examCode=exaresult.examCode WHERE exaresult.regNum=? and examYear=?");
            myStmt.setInt(1,myRs_mail.getInt("regNum"));
            myStmt.setInt(2,Integer.valueOf(textFieldSendEmailExamYear.getText()));
                
            myRs=myStmt.executeQuery();
        
        
            while(myRs.next())
            {
                email=myRs.getString("email");
                message+=myRs.getString("subCode")+"\t\t\t"+myRs.getString("grade")+"\n";
            }
            SendMail.mail(email,message);
            
        }
         AlertBox.display("Sucessful","You have sent Emails sucessfully");
    }
    
    //End Of Admin's Exam And Results Methods
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       datalec=FXCollections.observableArrayList(); 
       datains=FXCollections.observableArrayList();
       dataExamGrade=FXCollections.observableArrayList();
       dataExamSub=FXCollections.observableArrayList();
       dataAssignmentGrade=FXCollections.observableArrayList();
        
       empType.setValue("Lecturer");
       empType.getItems().addAll("Lecturer","Instructor");
       
       ExamYear.setValue("1");
       ExamYear.getItems().addAll("1","2","3","4");
       
       ExamSemester.setValue("1");
       ExamSemester.getItems().addAll("1","2");
       
       ExamDetailYear.setValue("1");
       ExamDetailYear.getItems().addAll("1","2","3","4");
       
       ExamDetailSemester.setValue("1");
       ExamDetailSemester.getItems().addAll("1","2");
       
       DeleteAssignmentSubjectType.setValue("Practical");
       DeleteAssignmentSubjectType.getItems().addAll("Practical","Non Practical");
       
       AssignmentGradeSubjectType.setValue("Practical");
       AssignmentGradeSubjectType.getItems().addAll("Practical","Non Practical");
       
       lecTeaching.setEditable(true);
       lecEmpId.setCellFactory(TextFieldTableCell.forTableColumn());
       lecSubCode.setCellFactory(TextFieldTableCell.forTableColumn());
       lecLocation.setCellFactory(TextFieldTableCell.forTableColumn());
       lecStartTime.setCellFactory(TextFieldTableCell.forTableColumn());
       lecDay.setCellFactory(TextFieldTableCell.forTableColumn());
       
       
       insTeaching.setEditable(true);
       insEmpId.setCellFactory(TextFieldTableCell.forTableColumn());
       insSubCode.setCellFactory(TextFieldTableCell.forTableColumn());
       insLabId.setCellFactory(TextFieldTableCell.forTableColumn());
       insStartTime.setCellFactory(TextFieldTableCell.forTableColumn());
       insDay.setCellFactory(TextFieldTableCell.forTableColumn());
       
       tableExamSubGrade.setEditable(true);
       columnExamSubRegNum.setCellFactory(TextFieldTableCell.forTableColumn());
       columnExamSubGrade.setCellFactory(TextFieldTableCell.forTableColumn());
       
       tableExamSubDetails.setEditable(true);
       columnExamSubDetailsSubCode.setCellFactory(TextFieldTableCell.forTableColumn());
       columnExamSubDetailsStartingDay.setCellFactory(TextFieldTableCell.forTableColumn());
       
       tableAssignmentGrade.setEditable(true);
       columnAssignmentGradeRegNum.setCellFactory(TextFieldTableCell.forTableColumn());
       columnAssignmentGradeInClass.setCellFactory(TextFieldTableCell.forTableColumn());
       columnAssignmentGradeTakeHome.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    
    public boolean checkType(int reg)
    {
       
        String num=Integer.toString(reg);
        return num.charAt(2)!= '1';
      
            
    }
    
    public void setAdmin(int num)
     {
         adminReg=num;
     }
     
    //Start Admin's Student Display Methods
    
    //Displaying Student's Personal Data Button Method
    public void displayData(int reg) throws SQLException
     {
            connection = DbConnection.connect();
            myStmt=null;
            myRs=null;
            Student student=null;
           
           
            textFieldRegistration.setText(Integer.toString(reg));

            try {
                myStmt= connection.prepareStatement("SELECT * FROM student INNER JOIN FACULTY ON student.facId=faculty.IdFac  WHERE regNum = ?");
                myStmt.setInt(1,reg);
                myRs=myStmt.executeQuery();

                if(myRs.next())
                {
                    student=new Student(myRs.getInt("regNum"),myRs.getString("firstName"),myRs.getString("lastName"),myRs.getString("email"),myRs.getString("nic"),myRs.getInt("telephone"),myRs.getString("facName"),myRs.getString("courseId"));

                    name.setText(student.getFirstName()+student.getLastName());
                    faculty.setText(student.getFacId());
                    email.setText(student.getEmail());
                    telephone.setText(String.valueOf(student.getTelephone()));
                    course.setText(student.getCourseId());
                    nic.setText(student.getNic());
                }
              

            } catch (SQLException ex) {
                Logger.getLogger(StudentProfileController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
     }
    
    //Displaying Student's Undergraduate Button Method
    public void displayUndergraduateData(int reg) throws SQLException
     {
            connection = DbConnection.connect();
            myStmt=null;
            myRs=null;
            Undergraduate undergraduate=null;
            
            myStmt= connection.prepareStatement("SELECT * FROM undergraduate WHERE regNum = ?");
            myStmt.setInt(1,reg);
            myRs=myStmt.executeQuery();
            
            if(myRs.next())
            {
                undergraduate=new Undergraduate(myRs.getString("subject1"),myRs.getString("subject2"),myRs.getString("subject3"),myRs.getString("grade1"),myRs.getString("grade2"),myRs.getString("grade3"),myRs.getInt("rank"),myRs.getFloat("zScore"));
            
                textFieldSubject1.setText(undergraduate.getSubject1());
                textFieldSubject2.setText(undergraduate.getSubject2());
                textFieldSubject3.setText(undergraduate.getSubject3());
                textFieldGrade1.setText(undergraduate.getGrade1());
                textFieldGrade2.setText(undergraduate.getGrade2());
                textFieldGrade3.setText(undergraduate.getGrade3());
                textFieldZscore.setText(String.valueOf(undergraduate.getzScore()));
                textFieldRank.setText(String.valueOf(undergraduate.getRank()));
            }
            else
            {
               AlertBox.display("Error","Invalid Undergraduate Registration Number");  
            }
            
     }
     
    
    //Display Stduent's Editting Window View Button Method
    public void displayEditData(int reg) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        
        myStmt= connection.prepareStatement("SELECT * FROM student WHERE regNum = ?");
        myStmt.setInt(1,regNum);
        myRs=myStmt.executeQuery();
        
        if(myRs.next())
        {
            textFieldEditFirst.setText(myRs.getString("firstName"));
            textFieldEditSecond.setText(myRs.getString("lastName"));
            textFieldEditEmail.setText(myRs.getString("email"));
            textFieldEditTelephone.setText(myRs.getString("telephone"));
            textFieldEditNic.setText(myRs.getString("nic"));
        }
        
    }
    
    //Display Stduent's Postgraduate Data Editting Window View Button Method
    public void displayPostgraduateData(int reg) throws SQLException
     {
            connection = DbConnection.connect();
            myStmt=null;
            myRs=null;
           
            myStmt= connection.prepareStatement("SELECT * FROM postgraduate WHERE regNum = ?");
            myStmt.setInt(1,reg);
            myRs=myStmt.executeQuery();
            
            if(myRs.next())
            {
               textFieldEditQualification.setText(myRs.getString("qualificationType"));
                textFieldEditYearOfCmp.setText(myRs.getString("yearCmp"));
                textFieldEditInstitute.setText(myRs.getString("institue"));   
            }
            else
            {
               AlertBox.display("Error","Invalid Undergraduate Registration Number");  
            }
     }
    
    // end Of admin's Student display methods
     
    
    
    //Start Of Admin's Faculty Methods
    
    //View Faculty Data Button Method
    public void displayFacData() throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        data=FXCollections.observableArrayList();
        
        myStmt= connection.prepareStatement("SELECT * FROM faculty "); 
        myRs=myStmt.executeQuery();
        
        while(myRs.next())
        {
               
                data.add(new Faculty(myRs.getString("IdFac"),myRs.getString("facName")));
        }
        
        columnFacId.setCellValueFactory(new PropertyValueFactory<>("facId"));
        columnFacName.setCellValueFactory(new PropertyValueFactory<>("facName"));
        
    
        tableFaculty.setItems(null);
        tableFaculty.setItems(data);   
     }
    // End Of Admin's Faculty Methods
     
    
    
    //Start Of Admin's Employee Methods
    //View Employee Data Method
    public void displayEmpData(String emp) throws SQLException
     {
            connection = DbConnection.connect();
            myStmt=null;
            myRs=null;
            Employee employee;
           
            myStmt= connection.prepareStatement("SELECT * FROM employee WHERE empId = ?");
            myStmt.setInt(1,Integer.valueOf(emp));
            myRs=myStmt.executeQuery();
            
            if(myRs.next())
            {
                employee=new Employee(Integer.valueOf(emp),myRs.getString("name"),myRs.getString("type"),myRs.getString("address"),myRs.getString("email"),myRs.getInt("telephone"));
                labelEmpType.setText(String.valueOf(employee.getEmpId()));
                labelEmpName.setText(employee.getName());
                labelEmpAddress.setText(employee.getAddress());
                labelEmpEmail.setText(employee.getEmail()); 
                labelEmpTelephone.setText(String.valueOf(employee.getTelephone()));
            }
            else
            {
               AlertBox.display("Error","Invalid Employee ID");  
            }
     }
    
    //Insert Dummy Data Row In Lectuerer's Teaching Detail Table
     public void AddLecTeachingDetails()
     {
        datalec.add(new LecturerTeaching("EmpId","SubjectCode","Location","StartTime","Day"));
       
        lecEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        lecSubCode.setCellValueFactory(new PropertyValueFactory<>("SubCode"));
        lecLocation.setCellValueFactory(new PropertyValueFactory<>("Location"));
        lecStartTime.setCellValueFactory(new PropertyValueFactory<>("StartTime"));
        lecDay.setCellValueFactory(new PropertyValueFactory<>("Day"));
        
       
        lecTeaching.setItems(null);
        lecTeaching.setItems(datalec);   
     }
     
     //Insert Dummy Data Row In instructore's Teaching Detail Table
     public void AddInsTeachingDetails()
     {
        datains.add(new InstructorTeaching("0","SubjectCode","Location","StartTime","Day"));
   
        insEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        insSubCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        insLabId.setCellValueFactory(new PropertyValueFactory<>("labId"));
        insStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        insDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        
        
        
        insTeaching.setItems(null);
        insTeaching.setItems(datains);   
     }
     
    // End Of Admin's Employee Methods
    
     
    //Start Of Admin's Course & Subject Methods
    //View Course Details 
    public void displayCourseData(String courseId) throws SQLException
     {
            connection = DbConnection.connect();
            myStmt=null;
            myRs=null;
            Course course=null;
           
            myStmt= connection.prepareStatement("SELECT * FROM course WHERE courseId = ?");
            myStmt.setString(1,courseId);
            myRs=myStmt.executeQuery();
            
            if(myRs.next())
            {
                
                String facId=myRs.getString("IdFac");
                String fac = null;
                switch(facId)
                {
                    case("Cmp"):
                        fac="Computing";
                        break;
                    case("Bus"):
                        fac="Business";
                        break;
                    case("Eng"):
                        fac="Enginerring";
                        break;
                }
                
                course=new Course(courseId,myRs.getString("courseName"),fac,myRs.getInt("NoOfYear"),myRs.getString("courseType"));
                textFieldCourseFaculty.setText(course.getFaculty());
                textFieldCourseName.setText(course.getCourseName());
                textFieldCourseDuration.setText(String.valueOf(course.getDuration()));
                textFieldCourseType.setText(course.getCourseType()); 
            }
            else
            {
               AlertBox.display("Error","Invalid course ID");  
            }
     }
    
    //View Subject details
    public void  displaySubjectData(String subjectCode) throws SQLException
     {
            connection = DbConnection.connect();
            myStmt=null;
            myRs=null;
            Subject subject;
            
            myStmt= connection.prepareStatement("SELECT * FROM subject INNER JOIN course ON subject.courseId=course.courseId WHERE subCode = ?");
            myStmt.setString(1,subjectCode);
            myRs=myStmt.executeQuery();
            
            if(myRs.next())
            {
                subject=new Subject(subjectCode,myRs.getString("name"),myRs.getInt("credits"),myRs.getInt("fee"),myRs.getString("courseId"),myRs.getInt("Year"),myRs.getInt("semester"),myRs.getString("courseName"));

                textFieldSubjectName.setText(subject.getSubName());
                textFieldSubjectCourseId.setText(subject.getSubName()+" "+subject.getCourseId());
                textFieldSubjectYear.setText(String.valueOf(subject.getYear()));
                textFieldSubjectSemester.setText(String.valueOf(subject.getSemester()));
                textFieldSubjectCredits.setText(String.valueOf(subject.getCredit())); 
                textFieldSubjectFee.setText(String.valueOf(subject.getFee()));
                
                textFieldSubjectCourseId.setEditable(false);
                textFieldSubjectCourseId.setMouseTransparent(true);
                textFieldSubjectCourseId.setFocusTraversable(false);
            }
         
            else
            {
               AlertBox.display("Error","Invalid course ID");  
            }
     }
    //End Of Admin's Course and Subject Methods
    
    //Start of Admin's Exam And Grade Methods
    
    //Display Student's Exam Grade Method
     public void displayExamGrades(int regNum,int year,int sem) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        datagrade=FXCollections.observableArrayList();
        
        myStmt= connection.prepareStatement("SELECT * FROM exam INNER JOIN exaresult ON exam.examCode=exaresult.examCode WHERE (exaresult.regNum=?) AND (exam.year=?) AND (exam.semester=?)");
        myStmt.setInt(1,regNum);
        myStmt.setInt(2,year);
        myStmt.setInt(3,sem);
        myRs=myStmt.executeQuery();
        
        while(myRs.next())
        {
           datagrade.add(new ExamGradeStudent(myRs.getString("subCode"),myRs.getString("grade"),myRs.getInt("year"),myRs.getInt("semester")));
        }
        
        columnExamSubCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        columnExamGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        columnExamYear.setCellValueFactory(new PropertyValueFactory<>("year"));
        columnExamSemester.setCellValueFactory(new PropertyValueFactory<>("semester"));
        
        
        
        tableExamGrade.setItems(null);
        tableExamGrade.setItems(datagrade);   
    }
     
    //Adding Dummy Data For add exam record in the table
    public void AddSubGradeDetails()
    {
        dataExamGrade.add(new SubGrades(textFieldExamSubCode.getText(),textFieldExamSubExamCode.getText(),"RegNum","Grade"));
        
        columnExamSubRegNum.setCellValueFactory(new PropertyValueFactory<>("regNum"));
        columnExamSubGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));
        
        tableExamSubGrade.setItems(null);
        tableExamSubGrade.setItems(dataExamGrade);         
       
    }
    
    //Display Exam Subject Details (Timetable) and Exam Details
    public void displayExamSubDetails(String examYear,String year,String semester) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        dataExamSub=FXCollections.observableArrayList();
        ExamDetails examDetails;
        
        myStmt= connection.prepareStatement("SELECT * FROM exam INNER JOIN examSub ON exam.examCode=examsub.examCode WHERE exam.examYear=? AND year=? AND semester=?");
        myStmt.setString(1,examYear);
        myStmt.setInt(2,Integer.valueOf(year));
        myStmt.setInt(3,Integer.valueOf(semester));
        myRs=myStmt.executeQuery();
        
        
        while(myRs.next())
        {
           examDetails=new ExamDetails(myRs.getString("examCode"),myRs.getString("startingDay"),myRs.getInt("fee"),myRs.getInt("year"),myRs.getInt("semester"),myRs.getInt("examYear")); 
           textfieldExamDetailExamStartDate.setText(examDetails.getStartDay());
           textfieldExamDetailExamFee.setText(String.valueOf(examDetails.getFee()));
           textfieldExamDetailExamCode.setText(examDetails.getExamCode());
           dataExamSub.add(new ExamSubDetails(myRs.getString("subCode"),myRs.getString("examDate"),myRs.getString("examCode")));
        }
        
        columnExamSubDetailsSubCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        columnExamSubDetailsStartingDay.setCellValueFactory(new PropertyValueFactory<>("startingDay"));
        
        tableExamSubDetails.setItems(null);
        tableExamSubDetails.setItems(dataExamSub);
    }
    
     
    //Add Dummy Record To The Exam Subject Table
    public void AddExamSubDetails()
     {
        dataExamSub.add(new ExamSubDetails("SubCode","Exam Date",textfieldExamDetailExamCode.getText()));
  
        columnExamSubDetailsSubCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        columnExamSubDetailsStartingDay.setCellValueFactory(new PropertyValueFactory<>("startingDay"));
        
        tableExamSubDetails.setItems(null);
        tableExamSubDetails.setItems(dataExamSub);   
     }
    
    //Display Assignment Grades
    public void displayAssignmentGrades(String subCode,String subjectType,String year) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        dataAssignmentGrade=FXCollections.observableArrayList();
        
        if(AssignmentGradeSubjectType.getValue()=="Practical")
        {
           myStmt= connection.prepareStatement("SELECT * FROM practicalsub WHERE subCode=? AND year=?");
        }
        else
        {
             myStmt= connection.prepareStatement("SELECT * FROM nonpracticalsub WHERE subCode=? AND year=?");
        }
        
        myStmt.setString(1,subCode);
        myStmt.setInt(2,Integer.valueOf(year));
        myRs=myStmt.executeQuery();
        
        if(AssignmentGradeSubjectType.getValue()=="Practical")
        {
        while(myRs.next())
        {
           dataAssignmentGrade.add(new Assignment(myRs.getString("subCode"),String.valueOf(myRs.getInt("regNum")),myRs.getString("labAssignment"),myRs.getString("project"),myRs.getString("year")));
        }
        }
        else
        {
            dataAssignmentGrade.add(new Assignment(myRs.getString("subCode"),String.valueOf(myRs.getInt("regNum")),myRs.getString("inClassAssignment"),myRs.getString("report"),myRs.getString("year")));
        }
        
        columnAssignmentGradeRegNum.setCellValueFactory(new PropertyValueFactory<>("regNum"));
        columnAssignmentGradeInClass.setCellValueFactory(new PropertyValueFactory<>("inClass"));
        columnAssignmentGradeTakeHome.setCellValueFactory(new PropertyValueFactory<>("takeHome"));
        
        tableAssignmentGrade.setItems(null);
        tableAssignmentGrade.setItems(dataAssignmentGrade);
        
    }
    //Insert Dummy Data To The Assignment Table
     public void AddAssignmentGradeDetails()
    {
        dataAssignmentGrade.add(new Assignment(textFieldAssignmentGradeSubCode.getText(),"Registration Number","In Class","Take Home",textFieldAssignmentGradeAssignmentYear.getText()));
        
        columnAssignmentGradeRegNum.setCellValueFactory(new PropertyValueFactory<>("regNum"));
        columnAssignmentGradeInClass.setCellValueFactory(new PropertyValueFactory<>("inClass"));
        columnAssignmentGradeTakeHome.setCellValueFactory(new PropertyValueFactory<>("takeHome"));
        
        tableAssignmentGrade.setItems(null);
        tableAssignmentGrade.setItems(dataAssignmentGrade);
        
    } 
   //End Of Admin's Exam And Results Methods
    
}
