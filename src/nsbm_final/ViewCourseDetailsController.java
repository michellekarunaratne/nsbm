package nsbm_final;

import nsbm_final.entity.Course;
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


public class ViewCourseDetailsController implements Initializable {

    @FXML
    private TableView<Course> tableViewCourse;
    
    @FXML 
    private TableColumn<Course,String> columnCourseId;
    
    @FXML 
    private TableColumn<Course,String>columnCourseName;
    
    @FXML 
    private TableColumn<Course,String>columnCourseFaculty;
    
    @FXML 
    private TableColumn<Course,Integer>columnCourseDuration;
    
    @FXML 
    private TableColumn<Course,String>columnCourseType;
    
    @FXML
    private TextField searchBox;
    
     
    private ObservableList<Course> data;
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
        
        FilteredList<Course> filterData = new FilteredList<>(data, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(viewCourse -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (viewCourse.getCourseId().toLowerCase().contains(typedText)) {

                    return true;
                }
                if (viewCourse.getCourseName().toLowerCase().contains(typedText)) {

                    return true;
                }
                if (viewCourse.getFaculty().toLowerCase().contains(typedText)) {
                    return true;
                }
                if (String.valueOf(viewCourse.getDuration()).toLowerCase().contains(typedText)) {
                    return true;
                }
                
                if (viewCourse.getCourseType().toLowerCase().contains(typedText)) {
                    return true;
                }

                return false;
            });
            SortedList<Course> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableViewCourse.comparatorProperty());
            tableViewCourse.setItems(sortedList);
                       
            
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
        
        myStmt= connection.prepareStatement("SELECT * FROM course");
        myRs=myStmt.executeQuery();
        while(myRs.next())
        {
            data.add(new Course(String.valueOf(myRs.getString("courseId")),myRs.getString("courseName"),myRs.getString("IdFac"),myRs.getInt("NoOfYear"),myRs.getString("courseType")));
        }
        
        columnCourseId.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        columnCourseName.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        columnCourseFaculty.setCellValueFactory(new PropertyValueFactory<>("faculty"));
        columnCourseDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        columnCourseType.setCellValueFactory(new PropertyValueFactory<>("courseType"));
        
        
        tableViewCourse.setItems(null);
        tableViewCourse.setItems(data);
    }
    
    public void setAdmin(int value)
    {
        adminReg=value;
    }
    
}
