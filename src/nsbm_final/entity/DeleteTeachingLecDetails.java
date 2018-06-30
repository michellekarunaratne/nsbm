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
import javafx.scene.control.CheckBox;
import nsbm_final.entity.DbConnection;

/**
 *
 * @author Michelle
 */
public class DeleteTeachingLecDetails {
    
    private final IntegerProperty empId;
    private final StringProperty subCode;
    private final StringProperty location;
    private final StringProperty startTime;
    private final StringProperty day;
    private final CheckBox select;
    
    private Connection connection;
    private PreparedStatement myStmt;
    private ResultSet myRs;
    
    public DeleteTeachingLecDetails(int empId,String subCode,String location,String startTime,String day,CheckBox select)
    {
        this.empId=new SimpleIntegerProperty(empId);
        this.subCode=new SimpleStringProperty(subCode);
        this.location=new SimpleStringProperty(location);
        this.startTime= new SimpleStringProperty(startTime);
        this.day=new SimpleStringProperty(day);
        this.select = select;
    }
    
    //getters
     public Integer getEmpId() {
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
    
    public String getDay() {
        return day.get();
    }
    
     public CheckBox getSelect() {
        return select;
    }
     
     
    //setters
    
    public void setEmpId(int value)
    {
        empId.set(value);
    }
    
    public void setSubCode(String value)
    {
        subCode.set(value);
    }
    
    public void setLocation(String value)
    {
        location.set(value);
    }
    
    public void setStartTime(String value)
    {
        startTime.set(value);
    }
    
    public void setDay(String value)
    {
        day.set(value);
    }
    
     //property values
    
    public StringProperty subCodeProperty()
    {
        return subCode;
    }
    
    public IntegerProperty empIdProperty()
    {
        return empId;
    }
    
    public StringProperty locationProperty()
    {
        return location;
    }
    
    public StringProperty startTimeProperty()
    {
        return startTime;
    }
    
    public int deleteLectuererTeachingDetails() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myStmt=null;
        int rowsAffected;
        
        myStmt= connection.prepareStatement("DELETE FROM lecteaching WHERE empId=? AND subCode=?");
        
        myStmt.setInt(1,this.getEmpId());
        myStmt.setString(2,this.getSubCode());
        
        rowsAffected=myStmt.executeUpdate(); 
        return rowsAffected;
        
    }
    
}
