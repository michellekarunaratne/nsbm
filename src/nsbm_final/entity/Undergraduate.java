/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final.entity;

import nsbm_final.entity.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nsbm_final.utility.AlertBox;
import nsbm_final.entity.DbConnection;


public class Undergraduate extends Student
{
    String subject1,subject2,subject3,grade1,grade2,grade3;
    int rank;
    float zScore;
    
    private PreparedStatement myStmt;
    private ResultSet myRs;
    private Connection connection;
       
    public Undergraduate( int regNum, String firstName, String lastName, String email, String nic, int telephone, String facId, String courseId,String subject1, String subject2, String subject3, String grade1, String grade2, String grade3, float zScore, int rank)
    {
        super(regNum, firstName, lastName, email, nic, telephone, facId, courseId);
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.zScore = zScore;
        this.rank = rank;
    }
    
    public Undergraduate(String subject1, String subject2, String subject3, String grade1, String grade2, String grade3, int rank, float zScore) {
        
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.rank = rank;
        this.zScore = zScore;
    }

    public Undergraduate( int regNum,String subject1, String subject2, String subject3, String grade1, String grade2, String grade3, int rank, float zScore) {
        super(regNum);
        this.subject1 = subject1;
        this.subject2 = subject2;
        this.subject3 = subject3;
        this.grade1 = grade1;
        this.grade2 = grade2;
        this.grade3 = grade3;
        this.rank = rank;
        this.zScore = zScore;
    }
    
    //getters
    public String getSubject1() {
        return subject1;
    }

    public String getSubject2() {
        return subject2;
    }

    public String getSubject3() {
        return subject3;
    }

    public String getGrade1() {
        return grade1;
    }

    public String getGrade2() {
        return grade2;
    }

    public String getGrade3() {
        return grade3;
    }

    public int getRank() {
        return rank;
    }

    public float getzScore() {
        return zScore;
    }

    //setters
    public void setSubject1(String subject1) {
        this.subject1 = subject1;
    }

    public void setSubject2(String subject2) {
        this.subject2 = subject2;
    }

    public void setSubject3(String subject3) {
        this.subject3 = subject3;
    }

    public void setGrade1(String grade1) {
        this.grade1 = grade1;
    }

    public void setGrade2(String grade2) {
        this.grade2 = grade2;
    }

    public void setGrade3(String grade3) {
        this.grade3 = grade3;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setzScore(float zScore) {
        this.zScore = zScore;
    }
    
    
    
    
    
    public boolean insertUndergraduateData() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        super.insertStudentData();
        myStmt=connection.prepareStatement("INSERT INTO undergraduate (regNum,subject1,subject2,subject3,grade1,grade2,grade3,zScore,rank) VALUES(?,?,?,?,?,?,?,?,?)");
        
        myStmt.setInt(1,super.getRegNum());
        myStmt.setString(2,subject1);
        myStmt.setString(3,subject2);
        myStmt.setString(4,subject3);
        myStmt.setString(5,grade1);
        myStmt.setString(6,grade2);
        myStmt.setString(7,grade3);
        myStmt.setFloat(8,zScore);
        myStmt.setInt(9,rank);
        
        rowsAffected=myStmt.executeUpdate();
        
        if(rowsAffected>0)
        {
              AlertBox.display("Sucessful","You have registered sucessfully");
              return true;
        }
        else
        {
              AlertBox.display("Error","Data Cannot Be Inserted");
              return false;
        }
    }
    
     public int updateUndergraduateData() throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;

        myStmt= connection.prepareStatement("UPDATE undergraduate SET subject1=?,subject2=?,subject3=?,grade1=?,grade2=?,grade3=?,zScore=?,rank=? WHERE regNum=?");

        myStmt.setString(1,subject1);
        myStmt.setString(2,subject2);
        myStmt.setString(3,subject3);
        myStmt.setString(4,grade1);
        myStmt.setString(5,grade2);
        myStmt.setString(6,grade3);
        myStmt.setFloat(7,zScore);
        myStmt.setInt(8,rank);
        myStmt.setInt(9,super.getRegNum());
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
     }
    
    
       
       
}
