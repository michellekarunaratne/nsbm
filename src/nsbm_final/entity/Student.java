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
import java.util.logging.Level;
import java.util.logging.Logger;
import nsbm_final.entity.DbConnection;
import nsbm_final.utility.AlertBox;

/**
 *
 * @author Michelle
 */
public class Student {
    
    private int regNum,telephone,fee;
    private String firstName,lastName,email,nic,facId,courseId,fac,enrollDate;
    
    private PreparedStatement myStmt;
    private ResultSet myRs;
    private Connection connection;
    
    public Student(){}

    public Student(int regNum,String firstName, String lastName, String email, String nic,int telephone, String facId, String courseId) {
        this.regNum = regNum;
        this.telephone = telephone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nic = nic;
        this.facId = facId;
        this.courseId = courseId;
    }

    public Student(int regNum, String firstName, String lastName, String email, String nic, int telephone) {
        this.regNum = regNum;
        this.telephone = telephone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nic = nic;
    }

    public Student(int regNum, int fee, String courseId, String enrollDate) {
        this.regNum = regNum;
        this.fee = fee;
        this.courseId = courseId;
        this.enrollDate = enrollDate;
    }
    
    
    
    

    public Student(int regNum) {
        this.regNum = regNum;
    }
    

    //setters
    public void setRegNum(int regNum) {
        this.regNum = regNum;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public void setFacId(String facId) {
        this.facId = facId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setFac(String fac) {
        this.fac = fac;
    }
    
    //getters

    public int getRegNum() {
        return regNum;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getNic() {
        return nic;
    }

    public String getFacId() {
        return facId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getFac() {
        return fac;
    }

    public String getFirstName() {
        return firstName;
    }
    
    
    
    
    public void insertStudentData() throws SQLException
    {
          connection = DbConnection.connect();
          myStmt=null;
          myRs=null;
          int rowsAffected;
           
          myStmt= connection.prepareStatement("INSERT INTO student (regNum,firstName,lastName,email,nic,telephone,facId,courseId) VALUES(?,?,?,?,?,?,?,?)");
          
          
          if(facId=="Computing")
              fac="Cmp";
          else if(facId=="Business")
              fac="Bus";
          else
              fac="Eng";
          
          myStmt.setInt(1,regNum);
          myStmt.setString(2,firstName);
          myStmt.setString(3,lastName);
          myStmt.setString(4,email);
          myStmt.setString(5,nic);
          myStmt.setInt(6,telephone);
          myStmt.setString(7,fac);
          myStmt.setString(8,courseId);
          
          rowsAffected=myStmt.executeUpdate();
          
    }
    
    public int updateStudentDetails() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        myStmt= connection.prepareStatement("UPDATE student SET firstName=? ,lastName=?,email=?,telephone=? WHERE regNum=?");
         
        myStmt.setString(1,firstName);
        myStmt.setString(2,lastName);
        myStmt.setString(3,email);
        myStmt.setInt(4,telephone);
        myStmt.setInt(5,regNum);
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
       
    }
    
    public int updateStudentDetailsAdmin() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        myStmt= connection.prepareStatement("UPDATE student SET firstName=? ,lastName=?,email=?,telephone=?,nic=? WHERE regNum=?");
        
        myStmt.setString(1,firstName);
        myStmt.setString(2,lastName);
        myStmt.setString(3,email);
        myStmt.setInt(4,telephone);
        myStmt.setString(5,nic);
        myStmt.setInt(6,regNum);
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
    }
    
     public int deleteStudentDetailsAdmin() throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        myStmt= connection.prepareStatement("DELETE FROM student WHERE regNum=?");
        myStmt.setInt(1,regNum);
        rowsAffected=myStmt.executeUpdate();
        
        return rowsAffected;
        
     }
     
     public int addStudentEnrollDetails() throws SQLException
     {
       
            connection = DbConnection.connect();
            myStmt=null;
            myRs=null;
            int rowsAffected;

            myStmt=connection.prepareStatement("INSERT INTO enroll ( regNum,courseId,enrollDate,amount)VALUES(?,?,?,?)");

            myStmt.setInt(1,regNum);
            myStmt.setString(2,courseId);
            myStmt.setString(3,enrollDate);
            myStmt.setInt(4,fee);

            rowsAffected=myStmt.executeUpdate();
            return rowsAffected;
        
        
     }
    
}
    
 
