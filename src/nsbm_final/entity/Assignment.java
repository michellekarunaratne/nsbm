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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import nsbm_final.entity.DbConnection;


public class Assignment {
  
   private  StringProperty subCode;
   private  StringProperty regNum;
   private  StringProperty inClass;
   private  StringProperty takeHome;
   private  StringProperty year;
   
   private PreparedStatement myStmt;
   private ResultSet myRs;
   private Connection connection;

  
    
    public Assignment(String subCode, String regNum, String inClass, String takeHome,String year)
    {
        this.subCode =new SimpleStringProperty(subCode);
        this.regNum = new SimpleStringProperty(regNum);
        this.inClass =new SimpleStringProperty(inClass);
        this.takeHome =new SimpleStringProperty(takeHome);
        this.year=new SimpleStringProperty(year);
    }

    public Assignment(String subCode, String regNum, String year) {
        this.subCode = new SimpleStringProperty(subCode);
        this.regNum = new SimpleStringProperty(regNum);
        this.year = new SimpleStringProperty(year);
    }
    
    
    
    //geters
    public String getSubCode() {
        return subCode.get();
    }

    public String getRegNum() {
        return regNum.get();
    }

    public String getInClass() {
        return inClass.get();
    }

    public String getTakeHome() {
        return takeHome.get();
    }
    public String getYear() {
        return year.get();
    }
    
    
    //setters
    
     public void setSubCode(String value) {
        subCode.set(value);
    }

    public void setRegNum(String value) {
        regNum.set(value);
    }

    public void setInClass(String value) {
        inClass.set(value);
    }

    public void setTakeHome(String value) {
        takeHome.set(value);
    }
     public void setYear(String value) {
        year.set(value);
    }
    
    //property values
    
    public StringProperty SubCodeProperty() {
        return subCode;
    }
    
    public StringProperty RegNumProperty() {
        return regNum;
    }
    
    public StringProperty InClassProperty() {
        return inClass;
    }
     
    public StringProperty TakeHomeProperty() {
        return takeHome;
    }
    
    public StringProperty YearProperty() {
        return year;
    }
    
    public int insertAssignmentGrades(String type) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected;
        
        if(type=="Practical")
        {
            myStmt= connection.prepareStatement("INSERT INTO practicalsub (regNum,subCode,labAssignment,project,year) VALUES(?,?,?,?,?)");
        }
        else
        {
            myStmt= connection.prepareStatement("INSERT INTO nonpracticalsub (regNum,subCode,inClass,report,year) VALUES(?,?,?,?,?)");
        }
        
        myStmt.setInt(1,Integer.valueOf(this.getRegNum()));
        myStmt.setString(2,this.getSubCode());
        myStmt.setString(3,this.getInClass());
        myStmt.setString(4,this.getTakeHome());
        myStmt.setInt(5,Integer.valueOf(this.getYear()));
        
        rowsAffected=myStmt.executeUpdate(); 
        return rowsAffected;
    }
    
    public int editAssignmentGrade(String type) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        if(type=="Practical")
        {
            myStmt= connection.prepareStatement("UPDATE practicalsub SET labAssignment=? ,project=? WHERE regNum=?");
        }
        else
        {
            myStmt= connection.prepareStatement("UPDATE practicalsub SET labAssignment=? ,project=? WHERE regNum=?");
        }
        
        myStmt.setString(1,this.getInClass());
        myStmt.setString(2,this.getTakeHome());
        myStmt.setInt(3,Integer.valueOf(this.getRegNum()));
        
        rowsAffected=myStmt.executeUpdate();  
        return rowsAffected;
    }
    
    public int deleteAssignmentGrade(String type) throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        if(type== "Practical")
        {
           myStmt= connection.prepareStatement("DELETE FROM practicalsub  WHERE regNum=? AND subCode=? AND year=?");
        }
        else
        {
            myStmt= connection.prepareStatement("DELETE FROM nonpracticalsub  WHERE regNum=? AND subCode=? AND year=?");
        }
        
         myStmt.setString(1,this.getRegNum());
         myStmt.setString(1,this.getSubCode());
         myStmt.setInt(1,Integer.valueOf(this.getYear()));
         
         rowsAffected=myStmt.executeUpdate();
         return rowsAffected;
    }
    
}
