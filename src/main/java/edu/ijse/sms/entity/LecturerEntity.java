package edu.ijse.sms.entity;

import java.util.List;

public class LecturerEntity {
    private String lecturerId;
    private String lecturerName;
    private List<String> subjectIds;

    public LecturerEntity() {
    }

    public LecturerEntity(String lecturerId, String lecturerName) {
        this.lecturerId = lecturerId;
        this.lecturerName = lecturerName;
    }

    // Getters and Setters
    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public List<String> getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(List<String> subjectIds) {
        this.subjectIds = subjectIds;
    }
}
