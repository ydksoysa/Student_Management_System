package edu.ijse.sms.dto;

import java.sql.Date;

public class AttendanceDto {
    private Date date;
    private String subjectId;
    private String studentId;
    private String status;

    public AttendanceDto() {
    }

    public AttendanceDto(Date date, String subjectId, String studentId, String status) {
        this.date = date;
        this.subjectId = subjectId;
        this.studentId = studentId;
        this.status = status;
    }

    // Getters and Setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
