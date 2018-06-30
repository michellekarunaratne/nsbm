/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.CheckBox;
import nsbm_final.entity.DbConnection;


public class Course {
    
    private  StringProperty courseId;
    private  StringProperty courseName;
    private  StringProperty faculty ;
    private  IntegerProperty duration;
    private  StringProperty courseType;
    
    private Connection connection;
    private PreparedStatement myStmt;
   
    public Course(String courseId,String courseName,String faculty,int duration,String courseType)
    {
        this.courseId=new SimpleStringProperty(courseId);
        this.courseName=new SimpleStringProperty(courseName);
        this.faculty=new SimpleStringProperty(faculty);
        this.duration=new SimpleIntegerProperty(duration);
        this.courseType=new SimpleStringProperty(courseType);
    }

    public Course(String courseId) {
        this.courseId =new SimpleStringProperty(courseId);
    }

     //getters
    public String getCourseId()
    {
        return courseId.get();
    }

    public String getCourseName() {
        return courseName.get();
    }

    public String getFaculty() {
        return faculty.get();
    }

    public Integer getDuration() {
        return duration.get();
    }
    
     public String getCourseType() {
        return courseType.get();
    }
    
    //setters
    
    public void setCourseId(String value)
    {
        courseId.set(value);
    }
    
    public void setCourseName(String value)
    {
        courseName.set(value);
    }
    
    public void setFaculty(String value)
    {
        faculty.set(value);
    }
    
    public void setDuration(int value)
    {
        duration.set(value);
    }
    
    public void setCourseType(String value)
    {
        courseType.set(value);
    }
    
    //property values
    
    public StringProperty courseIdProperty()
    {
        return courseId;
    }
    
    public StringProperty courseNameProperty()
    {
        return courseName;
    }
    
    public StringProperty facultyProperty()
    {
        return faculty;
    }
    
    public IntegerProperty durationProperty()
    {
        return duration;
    }
    
     public StringProperty courseTypeProperty()
    {
        return courseType;
    }
     
     public int insertCourseDetails() throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        myStmt= connection.prepareStatement("INSERT INTO course (courseId,IdFac,courseName,NoOfYear,courseType) VALUES(?,?,?,?,?)");
        
        myStmt.setString(1,this.getCourseId());
        myStmt.setString(2,this.getFaculty());
        myStmt.setString(3,this.getCourseName());
        myStmt.setInt(4,this.getDuration());
        myStmt.setString(5,this.getCourseType());
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
     }
     
     public int editCourseDetails() throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        myStmt= connection.prepareStatement("UPDATE course SET courseName=? ,IdFac=?,NoOfYear=?,courseType=? WHERE courseId=?");
        
        myStmt.setString(1,this.getCourseName());
        myStmt.setString(2,this.getFaculty());
        myStmt.setInt(3,this.getDuration());
        myStmt.setString(4,this.getCourseType());
        myStmt.setString(5,this.getCourseId());
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
     }
     
     public int deleteCourseDetails() throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        myStmt= connection.prepareStatement("DELETE FROM Course WHERE courseId=?");
        myStmt.setString(1,this.getCourseId());
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
        
     }
     
     
     
     
}
