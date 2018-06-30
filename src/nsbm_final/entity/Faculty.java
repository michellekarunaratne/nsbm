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


public class Faculty
{
    
    private final StringProperty facId;
    private final StringProperty facName;
    
    private PreparedStatement myStmt;
    private ResultSet myRs;
    private Connection connection;
   
    public Faculty(String facId, String facName)
    {
        this.facId=new SimpleStringProperty(facId);
        this.facName=new SimpleStringProperty(facName);
    }
    
     //getters
    public String getFacId()
    {
        return facId.get();
    }

    public String getFacName()
    {
        return facName.get();
    }
    
    //setters
    
    public void setFacId(String value)
    {
        facId.set(value);
    }
    
    public void setFacName(String value)
    {
        facName.set(value);
    }
    
    //property values
    
    public StringProperty facIdProperty()
    {
        return facId;
    }
    
    public StringProperty facNameProperty()
    {
        return facName;
    }
    
    public int updateFacultyData() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        myStmt= connection.prepareStatement("UPDATE faculty SET facName=? WHERE IdFac=?");
        myStmt.setString(1,facName.get());
        myStmt.setString(2,facId.get());
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
    }

    
}
