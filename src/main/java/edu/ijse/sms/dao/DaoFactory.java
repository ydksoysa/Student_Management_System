package edu.ijse.sms.dao;

import edu.ijse.sms.dao.custom.*;


public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory() {}

    public static DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }
        return instance;
    }

    public enum DAOType {
        STUDENT,SUBJECT,COURSE, LECTURER,ATTENDANCE // Add other DAO types as needed
    }

    public <T> T getDao(DAOType daoType) {
        switch (daoType) {
            case STUDENT:
                return (T) new StudentDaoImpl();// Assume StudentDaoImpl exists
            case COURSE:
                return (T) new CourseDaoImpl();
            case SUBJECT:
                return (T) new SubjectDaoImpl();
            case LECTURER:
                return (T) new LecturerDaoImpl();
            case ATTENDANCE:
                return (T) new AttendanceDaoImpl();
            default:
                return null;
        }
    }
}
