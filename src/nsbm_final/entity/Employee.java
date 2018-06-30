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


public class Employee {
    
    int empId,telephone;
    String type,name,address,email;
    
    private PreparedStatement myStmt;
    private ResultSet myRs;
    private Connection connection;

    public Employee(int empId,String name,String type,String address, String email,int telephone)
    {
        this.empId = empId;
        this.telephone = telephone;
        this.type = type;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    public Employee(int empId) {
        this.empId = empId;
    }
    
    

    //getters
    public int getEmpId() {
        return empId;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }
    
    //setters

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public int addEmployeeDetails() throws SQLException
    {
        connection = DbConnection.connect();
        myStmt=null;
        myRs=null;
        int rowsAffected;
        
        myStmt=connection.prepareStatement("INSERT INTO employee (empId,type,name,address,telephone,email) VALUES(?,?,?,?,?,?)");
        
        myStmt.setInt(1,empId);
        myStmt.setString(2,type);
        myStmt.setString(3,name);
        myStmt.setString(4,address);
        myStmt.setInt(5,telephone);
        myStmt.setString(6,email);
        
        rowsAffected=myStmt.executeUpdate();
        return rowsAffected;
        
    }
    
    
    
    
    
}
