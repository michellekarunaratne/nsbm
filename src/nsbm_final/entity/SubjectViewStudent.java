/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nsbm_final.entity;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;



/**
 *
 * @author Michelle
 */
public class SubjectViewStudent {
    
    private final StringProperty subCode;
    private final StringProperty subName;
    private final StringProperty course;
    private final StringProperty courseName;
    private final IntegerProperty year;
    private final IntegerProperty semester;
    private final IntegerProperty credit;
    private final IntegerProperty fee;
    
    public SubjectViewStudent(String subCode,String subName,String course,String courseName,int year,int semester,int credit,int fee)
    {
        this.subCode=new SimpleStringProperty(subCode);
        this.subName=new SimpleStringProperty(subName);
        this.course=new SimpleStringProperty(course);
        this.courseName=new SimpleStringProperty(courseName);
        this.year=new SimpleIntegerProperty(year);
        this.semester=new SimpleIntegerProperty(semester);
        this.credit=new SimpleIntegerProperty(credit);
        this.fee=new SimpleIntegerProperty(fee);
    }
    
     
    //getters
    public String getSubCode()
    {
        return subCode.get();
    }

    public String getSubName() {
        return subName.get();
    }
    
    public String getCourse() {
        return course.get();
    }
    
     public String getCourseName() {
        return courseName.get();
    }
    
    public Integer getYear() {
        return year.get();
    }
    
    public Integer getSemester() {
        return semester.get();
    }

    public Integer getCredit() {
        return credit.get();
    }

    public Integer getFee() {
        return fee.get();
    }
    
    //setters
    
    public void setSubCode(String value)
    {
        subCode.set(value);
    }
    
    public void setSubName(String value)
    {
        subName.set(value);
    }
    
    public void setCourse(String value)
    {
        course.set(value);
    }
    
    public void setCourseName(String value)
    {
        courseName.set(value);
    }
    
    public void setYear(int value)
    {
        year.set(value);
    }
    
    public void setSemester(int value)
    {
        semester.set(value);
    }
   
    public void setCredit(int value)
    {
        credit.set(value);
    }
    
    public void setfee(int value)
    {
        fee.set(value);
    }
    
    //property values
    
    public StringProperty subCodeProperty()
    {
        return subCode;
    }
    
    public StringProperty subNameProperty()
    {
        return subName;
    }
    
    public StringProperty courseProperty()
    {
        return course;
    }
    
    public StringProperty courseNameProperty()
    {
        return courseName;
    }
    
    public IntegerProperty yearProperty()
    {
        return year;
    }
    
    public IntegerProperty semesterProperty()
    {
        return semester;
    }
    
    public IntegerProperty creditProperty()
    {
        return credit;
    }
    
    public IntegerProperty feeProperty()
    {
        return fee;
    }
    
    
}
