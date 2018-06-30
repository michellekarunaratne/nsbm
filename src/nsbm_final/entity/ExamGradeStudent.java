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
import javafx.scene.control.CheckBox;
import nsbm_final.entity.DbConnection;

/**
 *
 * @author Michelle
 */
public class ExamGradeStudent {
    
    private  StringProperty subCode;
    private  StringProperty grade;
    private  IntegerProperty semester;
    private  IntegerProperty year;
    
    private PreparedStatement myStmt;
    private ResultSet myRs;
    private Connection connection;
    
    public ExamGradeStudent(String subCode,String grade,int year,int semester)
    {
        this.subCode=new SimpleStringProperty(subCode);
        this.grade=new SimpleStringProperty(grade);
        this.year=new SimpleIntegerProperty(year);
        this.semester=new SimpleIntegerProperty(semester);
    }
    
    //getters
    public String getSubCode()
    {
        return subCode.get();
    }

    public String getGrade() {
        return grade.get();
    }

    public Integer getYear() {
        return year.get();
    }

    public Integer getSemester() {
        return semester.get();
    }
  
    //setters
    public void setSubCode(String value)
    {
        subCode.set(value);
    }
    
    public void setGrade(String value)
    {
        grade.set(value);
    }
    
    public void setYear(int value)
    {
        year.set(value);
    }
    
    public void setSemester(int value)
    {
        semester.set(value);
    }
    
    //property values
    
    public StringProperty subCodeProperty()
    {
        return subCode;
    }
    
    public StringProperty gradeProperty()
    {
        return grade;
    }
    
    public IntegerProperty yearProperty()
    {
        return year;
    }
    
    public IntegerProperty semesterProperty()
    {
        return semester;
    }
    
   
}
