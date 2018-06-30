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
import nsbm_final.utility.AlertBox;
import nsbm_final.entity.DbConnection;

/**
 *
 * @author Michelle
 */
public class PostGraduate extends Student {

    String qualificationType,institue;
    int yearCmp;
    
    private PreparedStatement myStmt;
    private ResultSet myRs;
    private Connection connection;
    
    public PostGraduate(int regNum, String firstName, String lastName, String email, String nic, int telephone, String facId, String courseId,String qualificationType, String institue, int yearCmp)
    {
        super(regNum, firstName, lastName, email, nic, telephone, facId, courseId);
        this.qualificationType = qualificationType;
        this.institue = institue;
        this.yearCmp = yearCmp;
    }

    public PostGraduate(String qualificationType, String institue, int yearCmp) {
        this.qualificationType = qualificationType;
        this.institue = institue;
        this.yearCmp = yearCmp;
    }

    public PostGraduate( int regNum,String qualificationType, String institue, int yearCmp) {
        super(regNum);
        this.qualificationType = qualificationType;
        this.institue = institue;
        this.yearCmp = yearCmp;
    }
    

    
    
    //getters

    public String getQualificationType() {
        return qualificationType;
    }

    public String getInstitue() {
        return institue;
    }

    public int getYearCmp() {
        return yearCmp;
    }
    
    //setters

    public void setQualificationType(String qualificationType) {
        this.qualificationType = qualificationType;
    }

    public void setInstitue(String institue) {
        this.institue = institue;
    }

    public void setYearCmp(int yearCmp) {
        this.yearCmp = yearCmp;
    }
    
    
    public boolean insertPostGraduateData() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        super.insertStudentData();
        myStmt=connection.prepareStatement("INSERT INTO postgraduate (regNum,qualificationType,yearCmp,institue) VALUES(?,?,?,?)"); 
        
        myStmt.setInt(1,super.getRegNum());
        myStmt.setString(2,qualificationType);
        myStmt.setInt(3,yearCmp);
        myStmt.setString(4,institue);
        
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
    
     public int updatePostGraduateData() throws SQLException
     {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        myStmt= connection.prepareStatement("UPDATE postgraduate SET qualificationType=?,yearCmp=?,institue=? WHERE regNum=?");
        myStmt.setString(1,qualificationType);
        myStmt.setInt(2,yearCmp);
        myStmt.setString(3,institue);
        myStmt.setInt(4,super.getRegNum());
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
     }
    
    
}
