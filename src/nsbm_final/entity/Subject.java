/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;
import nsbm_final.entity.DbConnection;
import nsbm_final.utility.AlertBox;

/**
 *
 * @author Michelle
 */
public class Subject {
    
    private  StringProperty subCode;
    private  StringProperty subName;
    private  IntegerProperty credit;
    private  IntegerProperty fee;
    private  CheckBox select;
    private  StringProperty courseId;
    private  IntegerProperty year;
    private  IntegerProperty semester; 
    private  StringProperty courseName;
    private  IntegerProperty mode;
    
    private PreparedStatement myStmt;
    private ResultSet myRs;
    private Connection connection;
   
    
    public Subject(String subCode,String subName,int credit,int fee,CheckBox select)
    {
        this.subCode=new SimpleStringProperty(subCode);
        this.subName=new SimpleStringProperty(subName);
        this.credit=new SimpleIntegerProperty(credit);
        this.fee=new SimpleIntegerProperty(fee);
        this.select = select;
    }

    public Subject(String subCode, String subName, Integer credit, Integer fee, String courseId, Integer year, Integer semester,int mode) {
        this.subCode = new SimpleStringProperty(subCode);
        this.subName = new SimpleStringProperty(subName);
        this.credit = new SimpleIntegerProperty(credit);
        this.fee = new SimpleIntegerProperty(fee);
        this.courseId = new SimpleStringProperty(courseId);
        this.year = new SimpleIntegerProperty(year);
        this.semester = new SimpleIntegerProperty(semester);
        this.mode=new SimpleIntegerProperty(mode);
       
    }

     public Subject(String subCode, String subName, Integer credit, Integer fee, String courseId, Integer year, Integer semester,String courseName) {
        this.subCode = new SimpleStringProperty(subCode);
        this.subName = new SimpleStringProperty(subName);
        this.credit = new SimpleIntegerProperty(credit);
        this.fee = new SimpleIntegerProperty(fee);
        this.courseId = new SimpleStringProperty(courseId);
        this.year = new SimpleIntegerProperty(year);
        this.semester = new SimpleIntegerProperty(semester);
        this.courseName=new SimpleStringProperty(courseName);
    }

    public Subject(String subCode, String subName, Integer credit, Integer fee, Integer year, Integer semester) {
        this.subCode = new SimpleStringProperty(subCode);
        this.subName = new SimpleStringProperty(subName);
        this.credit = new SimpleIntegerProperty(credit);
        this.fee = new SimpleIntegerProperty(fee);
        this.year = new SimpleIntegerProperty(year);
        this.semester = new SimpleIntegerProperty(semester);
       
    }
    public Subject(String subCode)
    {
        this.subCode=new SimpleStringProperty(subCode);
    }
    
    
    //getters
    public String getSubCode()
    {
        return subCode.get();
    }

    public String getSubName() {
        return subName.get();
    }

    public Integer getCredit() {
        return credit.get();
    }

    public Integer getFee() {
        return fee.get();
    }
    
     public CheckBox getSelect() {
        return select;
    }

    public String getCourseId() {
        return courseId.get();
    }

    public Integer getYear() {
        return year.get();
    }

    public Integer getSemester() {
        return semester.get();
    }
    
     
    
    //setters
    
    public void setSubCode(String value)
    {
        subCode.set(value);
    }
    
    public void setSubName(String value)
    {
        subName.set(value);
    }
    
    public void setCredit(int value)
    {
        credit.set(value);
    }
    
    public void setfee(int value)
    {
        fee.set(value);
    }

    public void setCourseId(String value) {
        courseId.setValue(value);
    }

    public void setYear(int value) {
        year.setValue(value);
    }

    public void setSemester(int value) {
        semester.setValue(value);
    }
    
    
    
    
    //property values
    
    public StringProperty subCodeProperty()
    {
        return subCode;
    }
    
    public StringProperty subNameProperty()
    {
        return subName;
    }
    
    public IntegerProperty creditProperty()
    {
        return credit;
    }
    
    public IntegerProperty feeProperty()
    {
        return fee;
    }
    
    public StringProperty courseIdProperty()
    {
        return courseId;
    }
    
    public IntegerProperty yearProperty()
    {
        return year;
    }
    
    public IntegerProperty semesterProperty()
    {
        return semester;
    }
    
    public int addSubjectDetails() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected=0;
        
        myStmt=connection.prepareStatement("INSERT INTO subject (subCode,courseId,name,Year,semester,credits,fee) VALUES(?,?,?,?,?,?,?)");
        
        myStmt.setString(1,this.getSubCode());
        myStmt.setString(2,this.getCourseId());
        myStmt.setString(3,this.getSubName());
        myStmt.setInt(4,Integer.valueOf(this.getYear()));
        myStmt.setInt(5,Integer.valueOf(this.getSemester()));
        myStmt.setInt(6,Integer.valueOf(this.getCredit()));
        myStmt.setInt(7,Integer.valueOf(this.getFee()));
        
        rowsAffected=myStmt.executeUpdate();
        
        return rowsAffected;
    }
    
    public int editSubjectDetails() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected=0;
        
        myStmt= connection.prepareStatement("UPDATE subject SET name=?,Year=?,semester=?,credits=?,fee=? WHERE subCode=?");
        
        myStmt.setString(1,this.getSubName());
        myStmt.setInt(2,this.getYear());
        myStmt.setInt(3,this.getSemester());
        myStmt.setInt(4,this.getCredit());
        myStmt.setInt(5,this.getFee());
        myStmt.setString(6,this.getSubCode());
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
    }
    
    public int deleteSubjectDetails() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        myStmt= connection.prepareStatement("DELETE FROM subject WHERE subCode=?");
        myStmt.setString(1,this.getSubCode());
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
    }
   
    public int addEnrollSubjectDetails(int regNum,int year,int semester) throws SQLException
    {
        try
        {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        myStmt= connection.prepareStatement("INSERT INTO enrollsub (regNum,subCode,year,semester) VALUES(?,?,?,?)");
        
        myStmt.setInt(1,regNum);
        myStmt.setString(2,this.getSubCode());
        myStmt.setInt(3,year);
        myStmt.setInt(4,semester);
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
        }
        catch(SQLIntegrityConstraintViolationException ex)
        {
             AlertBox.display("Message","You Have Enrolled Selected Subject "+this.getSubCode()+" Already");
              return 0;
        }
        
    }
    
    
    
    
    
    
}
