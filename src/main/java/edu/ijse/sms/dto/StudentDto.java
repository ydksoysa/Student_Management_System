package edu.ijse.sms.dto;

public class StudentDto {

    private String regId;
    private String name;
    private String course;
    private String contactNum;

    // Getters, Setters, Constructors
    public StudentDto() {
    }

    // Full-arg constructor
    public StudentDto( String regId, String name, String course, String contactNum) {

        this.regId = regId;
        this.name = name;
        this.course = course;
        this.contactNum = contactNum;
    }


    public String getRegId() {
        return regId;
    }
    public String getRegisteredId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }
}
