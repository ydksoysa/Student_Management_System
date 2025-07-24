package edu.ijse.sms.entity;


    public class StudentEntity {
        private int id;
        private String regId;
        private String name;
        private String course;
        private String contactNum;

        // Getters, Setters, Constructors
        public StudentEntity() {
        }

        // Full-arg constructor
        public StudentEntity(int id, String regId, String name, String course, String contactNum) {
            this.id = id;
            this.regId = regId;
            this.name = name;
            this.course = course;
            this.contactNum = contactNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRegId() {
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
