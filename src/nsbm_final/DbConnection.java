/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DbConnection {

    static Connection getconnect() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    static Connection connect(){
    try{
        String url="jdbc:mysql://localhost:3306/enrollmentsystem?autoReconnect=true&useSSL=false";
        String user="root";
        String password="ucsc@123";
    
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn=DriverManager.getConnection(url,user,password);
        return conn;
    } catch(ClassNotFoundException | SQLException ex){
        Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE,null,ex);
    }
    return null;
    }
}
  
