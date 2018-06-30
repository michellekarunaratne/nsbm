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
import nsbm_final.entity.DbConnection;

/**
 *
 * @author Michelle
 */
public class ExamDetails {
    
    String examCode,startDay;
    int fee,year,semester,examYear;
    
    private PreparedStatement myStmt;
    private ResultSet myRs;
    private Connection connection;

    public ExamDetails(String examCode, String startDay, Integer fee, Integer year, Integer semester, Integer examYear) {
        this.examCode = examCode;
        this.startDay = startDay;
        this.fee = fee;
        this.year = year;
        this.semester = semester;
        this.examYear = examYear;
    }

    public ExamDetails(String examCode) {
        this.examCode = examCode;
    }
    
    
    //getters
    public String getExamCode() {
        return examCode;
    }

    public String getStartDay() {
        return startDay;
    }

    public int getFee() {
        return fee;
    }

    public int getYear() {
        return year;
    }

    public int getSemester() {
        return semester;
    }

    public int getExamYear() {
        return examYear;
    }
    
    //setters

    public void setExamCode(String examCode) {
        this.examCode = examCode;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public void setExamYear(int examYear) {
        this.examYear = examYear;
    }
    
    public int insertExamDetails() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected=0;
        
        myStmt= connection.prepareStatement("INSERT INTO exam (examCode,fee,startingDay,year,semester,examYear) VALUES(?,?,?,?,?,?)");
        
        myStmt.setString(1,examCode);
        myStmt.setInt(2,fee);
        myStmt.setString(3,startDay);
        myStmt.setInt(4,year);
        myStmt.setInt(5,semester);
        myStmt.setInt(6,examYear);
        rowsAffected=myStmt.executeUpdate();  
        return rowsAffected;
    }
    
    public int editExamDetails() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        myStmt= connection.prepareStatement("UPDATE exam SET fee=?,startingDay=?,year=?,semester=?,examYear=? WHERE examCode=?");
        
        myStmt.setInt(1,fee);
        myStmt.setString(2,startDay);
        myStmt.setInt(3,year);
        myStmt.setInt(4,semester);
        myStmt.setInt(5,examYear);
        myStmt.setString(6,examCode);
        
        rowsAffected=myStmt.executeUpdate(); 
        return rowsAffected;
    }
    
    public int deleteExamDetails() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        int rowsAffected = 0;
        
        myStmt= connection.prepareStatement("DELETE FROM exam WHERE examCode=?");
        myStmt.setString(1,examCode);
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
    }
    
}
