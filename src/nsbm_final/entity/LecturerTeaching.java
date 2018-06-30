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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import nsbm_final.entity.DbConnection;


public class LecturerTeaching {
    
    private StringProperty empId;
    private StringProperty subCode;
    private StringProperty location;
    private StringProperty startTime;
    private StringProperty day;
    
    private PreparedStatement myStmt;
    private ResultSet myRs;
    private Connection connection;
   
    
    public LecturerTeaching(String empId,String subCode,String location,String startTime,String day)
    {
        this.empId=new SimpleStringProperty(empId);
        this.subCode=new SimpleStringProperty(subCode);
        this.location=new SimpleStringProperty(location);
        this.startTime=new SimpleStringProperty(startTime);
        this.day=new SimpleStringProperty(day);
    }
    
    //Setters
    
    public void setEmpId(String value)
    {
        empId=new SimpleStringProperty(value);
    }
    
    public void setSubCode(String value)
    {
        subCode=new SimpleStringProperty(value);
    }
    
    public void setLocation(String value)
    {
        location=new SimpleStringProperty(value);
    }
    
    public void setStartTime(String value)
    {
        startTime=new SimpleStringProperty(value);
    }
    
    public void setDay(String value)
    {
        day=new SimpleStringProperty(value);
    }
    
     //getters
    
    public String getEmpId() {
        return empId.get();
    }
    
    public String getSubCode()
    {
        return subCode.get();
    }

    public String getLocation() {
        return location.get();
    }
    
    public String getStartTime() {
        return startTime.get();
    }
    
    public String getDay()
    {
        return day.get();
    }
    
    public int insertLecturerTeachingDetails() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        myStmt= connection.prepareStatement("INSERT INTO lecteaching (empId,subCode,location,start,day) VALUES(?,?,?,?,?)");
        
        myStmt.setInt(1,Integer.valueOf(this.getEmpId()));
        myStmt.setString(2,this.getSubCode());
        myStmt.setString(3,this.getLocation());
        myStmt.setString(4,this.getStartTime());
        myStmt.setString(5,this.getDay());
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
       
    }
   
    
}
