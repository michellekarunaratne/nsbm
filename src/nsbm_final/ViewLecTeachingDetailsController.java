
package nsbm_final;

import nsbm_final.entity.LecturerTeaching;
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

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class ViewLecTeachingDetailsController implements Initializable {

    @FXML
    private TableView<LecturerTeaching> tableViewTeachingLec;
    
    @FXML
    private TableColumn<LecturerTeaching,String> columnEmpId;
    
    @FXML
    private TableColumn<LecturerTeaching,String> columnSubjectCode;
    
    @FXML
    private TableColumn<LecturerTeaching,String> columnlocation;
    
    @FXML
    private TableColumn<LecturerTeaching,String> columnStartTime;
    
    @FXML
    private TableColumn<LecturerTeaching,String> columnDay;
    
    @FXML
    private TextField searchBox;
    
    private ObservableList<LecturerTeaching> data;
    private Connection connection;
    private PreparedStatement myStmt;
    private ResultSet myRs;
    int adminReg;
    
    Stage stage;
    Parent root;
    Scene scene;
    
    @FXML
    private void AdminButtonAction(ActionEvent event) throws IOException
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
        
        FilteredList<LecturerTeaching> filterData = new FilteredList<>(data, p -> true);
        searchBox.textProperty().addListener((obsevable, oldvalue, newvalue) -> {
            filterData.setPredicate(lecteaching -> {

                if (newvalue == null || newvalue.isEmpty()) {
                    return true;
                }
                String typedText = newvalue.toLowerCase();
                if (lecteaching.getEmpId().toLowerCase().contains(typedText)) {

                    return true;
                }
                if (lecteaching.getSubCode().toLowerCase().contains(typedText)) {

                    return true;
                }
                if (lecteaching.getLocation().toLowerCase().contains(typedText)) {
                    return true;
                }
                if (lecteaching.getStartTime().toLowerCase().contains(typedText)) {
                    return true;
                }
                if (lecteaching.getDay().toLowerCase().contains(typedText)) {
                    return true;
                }

                return false;
            });
            SortedList<LecturerTeaching> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(tableViewTeachingLec.comparatorProperty());
            tableViewTeachingLec.setItems(sortedList);
                       
            
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
        
        myStmt= connection.prepareStatement("SELECT * FROM lecteaching");
        myRs=myStmt.executeQuery();
        while(myRs.next())
        {
            data.add(new LecturerTeaching(String.valueOf(myRs.getInt("empId")),myRs.getString("subCode"),myRs.getString("location"),myRs.getString("start"),myRs.getString("day")));
        }
        
        columnEmpId.setCellValueFactory(new PropertyValueFactory<>("empId"));
        columnSubjectCode.setCellValueFactory(new PropertyValueFactory<>("subCode"));
        columnlocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        columnDay.setCellValueFactory(new PropertyValueFactory<>("day"));
        
        tableViewTeachingLec.setItems(null);
        tableViewTeachingLec.setItems(data);
    }
    
    public void setAdmin(int value)
    {
        adminReg=value;
    }
    
}
