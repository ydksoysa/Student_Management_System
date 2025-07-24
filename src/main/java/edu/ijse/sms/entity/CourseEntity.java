package edu.ijse.sms.entity;

import java.util.List;

public class CourseEntity {
    private String courseId;
    private String courseName;
    private List<String> subjectIds; // For holding associated subject IDs

    public CourseEntity() {
    }

    public CourseEntity(String courseId, String courseName) {
        this.courseId = courseId;
        this.courseName = courseName;
    }

    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public List<String> getSubjectIds() {
        return subjectIds;
    }

    public void setSubjectIds(List<String> subjectIds) {
        this.subjectIds = subjectIds;
    }
}
