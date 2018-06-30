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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import nsbm_final.entity.DbConnection;

/**
 *
 * @author Michelle
 */
public class ExamSubDetails {
    
   private StringProperty subCode;
   private StringProperty startingDay;
   private StringProperty examCode;
   
   private PreparedStatement myStmt;
   private ResultSet myRs;
   private Connection connection;
   
   public ExamSubDetails(String subCode,String startingDay,String examCode)
   {
       this.subCode=new SimpleStringProperty(subCode);
       this.startingDay=new SimpleStringProperty(startingDay);
       this.examCode=new SimpleStringProperty(examCode);
   }

    public ExamSubDetails(String subCode, String examCode) {
        this.subCode = new SimpleStringProperty(subCode);
        this.examCode = new SimpleStringProperty(examCode);
    }
   
   
   //getters
   public String getSubCode() {
        return subCode.get();
    }
    public String getStartingDay() {
        return startingDay.get();
    }

    public String getExamCode() {
        return examCode.get();
    }
    
   
    //setters
    public void setSubCode(String value)
    {
        subCode.set(value);
    }

    public void setStartingDay(String value) 
    {
        startingDay.set(value);
    }

    public void setExamCode(String value) {
        examCode.set(value);
    }
    
    
    //property values
    
    public StringProperty SubCodeProperty() {
        return subCode;
    }
    
    public StringProperty StartingDayProperty() {
        return startingDay;
    }
    
     public StringProperty examCodeDayProperty() {
        return examCode;
    }
     
     
     public int insertExamSubDetails() throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected;
        myStmt=connection.prepareStatement("INSERT INTO examsub (examCode,subCode,examDate) VALUES(?,?,?)");   
        
        myStmt.setString(1,this.getExamCode());
        myStmt.setString(2,this.getSubCode());
        myStmt.setString(3,this.getStartingDay());
        
        rowsAffected=myStmt.executeUpdate(); 
        return rowsAffected;
     }
     
     public int editExamSubDetails() throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        myStmt=connection.prepareStatement("UPDATE examsub SET examDate=? WHERE subCode=? AND examCode=?");   
        
        myStmt.setString(1,this.getStartingDay());
        myStmt.setString(2,this.getSubCode());
        myStmt.setString(3,this.getExamCode());
        rowsAffected=myStmt.executeUpdate(); 
        return rowsAffected;
     }
     
     public int deleteExamSubDetails() throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        myStmt= connection.prepareStatement("DELETE FROM examsub WHERE examCode=? AND subCode=?");
        
        myStmt.setString(1,this.getExamCode());
        myStmt.setString(2,this.getSubCode());
        
        rowsAffected=myStmt.executeUpdate(); 
        return rowsAffected;
        
     }
    
    
    
   
}
