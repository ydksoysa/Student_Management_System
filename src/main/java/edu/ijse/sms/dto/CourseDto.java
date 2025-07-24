package edu.ijse.sms.dto;

import java.util.List;

public class CourseDto {
    private String courseId;
    private String courseName;
    private List<String> subjectIds;
    private List<String> subjectNames;

    public CourseDto() {
    }

    public CourseDto(String courseId, String courseName) {
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
    public List<String> getSubjectNames() {
        return subjectNames;
    }

    public void setSubjectNames(List<String> subjectNames) {
        this.subjectNames = subjectNames;
    }
    public String getSubjectsFormatted() {
        return subjectNames != null ? String.join(", ", subjectNames) : "";
    }
}
