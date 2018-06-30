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

/**
 *
 * @author Michelle
 */
public class SubGrades {
    
   private  StringProperty subCode;
   private  StringProperty examCode;
   private  StringProperty regNum;
   private  StringProperty grade;
   
   private PreparedStatement myStmt;
   private ResultSet myRs;
   private Connection connection;
   
   public SubGrades(String subCode,String examCode,String regNum,String grade)
   {
       this.subCode=new SimpleStringProperty(subCode);
       this.examCode=new SimpleStringProperty(examCode);
       this.regNum=new SimpleStringProperty(regNum);
       this.grade=new SimpleStringProperty(grade);
   }

    public SubGrades(String regNum,String subCode,String examCode) {
        this.subCode =new SimpleStringProperty( subCode);
        this.examCode = new SimpleStringProperty(examCode);
        this.regNum = new SimpleStringProperty(regNum);
    }
   
   
   
      
    //getters
    public String getSubCode()
    {
        return subCode.get();
    }

    public String getExamCode() {
        return examCode.get();
    }
    
    public String getRegNum() {
        return regNum.get();
    }
    
     public String getGrade() {
        return grade.get();
    }
     
    //setters
    public void setSubCode(String value)
    {
        subCode.set(value);
    }

    public void setExamCode(String value)
    {
        examCode.set(value);
    }
    
    public void setRegNum(String value) 
    {
        regNum.set(value);
    }
    
     public void setGrade(String value) 
     {
        grade.set(value);
    }
     
     //property values
    
    public StringProperty subCodeProperty()
    {
        return subCode;
    }
    
    public StringProperty subNameProperty()
    {
        return examCode;
    }
    
    public StringProperty courseProperty()
    {
        return regNum;
    }
    
    public StringProperty courseNameProperty()
    {
        return grade;
    }
    
    public int insertExamSubGrade() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myStmt= connection.prepareStatement("INSERT INTO exaresult (regNum,subCode,examCode,grade) VALUES(?,?,?,?)");
        int rowsAffected=0;
  
        myStmt.setInt(1,Integer.valueOf(this.getRegNum()));
        myStmt.setString(2,this.getSubCode());
        myStmt.setString(3,this.getExamCode());
        myStmt.setString(4,this.getGrade());
        
        rowsAffected=myStmt.executeUpdate();  
        return rowsAffected;
    }
    
    public int editExamSubGrade() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        
        myStmt= connection.prepareStatement("UPDATE exaresult SET grade=? WHERE regNum=?");
        
        myStmt.setString(1,this.getGrade());
        myStmt.setInt(2,Integer.valueOf(this.getRegNum())); 
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
    }
    
    public int deleteExamGrade() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        myStmt= connection.prepareStatement("DELETE FROM exaresult WHERE regNum=? AND subCode=?");
        myStmt.setInt(1,Integer.valueOf(this.getRegNum()));
        myStmt.setString(2,this.getSubCode());
        
        rowsAffected=myStmt.executeUpdate(); 
        return rowsAffected;
    }
}
