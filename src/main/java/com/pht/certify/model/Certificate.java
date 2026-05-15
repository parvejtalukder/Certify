package com.pht.certify.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Certificate {

    @Id
    private String id;
    private String studentName;  
    private String studentId;
    private String course;       
    private String grade;        
    private String issueDate;    
    private String issuer; 

    Certificate(String studentName, String studentId, String course, String grade, String issueDate, String issuer) {
        // this.id = id;
        this.studentName = studentName;
        this.studentId = studentId;
        this.course = course;
        this.grade = grade;
        this.issueDate = issueDate;
        this.issuer = issuer;
    }
    
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourse() {
        return this.course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getGrade() {
        return this.grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIssueDate() {
        return this.issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssuer() {
        return this.issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

}
