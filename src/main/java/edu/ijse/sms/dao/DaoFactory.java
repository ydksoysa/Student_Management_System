package edu.ijse.sms.dao;

import edu.ijse.sms.dao.custom.StudentDaoImpl;

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
        STUDENT, COURSE, LECTURER // Add other DAO types as needed
    }

    public <T> T getDao(DAOType daoType) {
        switch (daoType) {
            case STUDENT:
                return (T) new StudentDaoImpl();// Assume StudentDaoImpl exists
            case COURSE:
                // Add CourseDaoImpl when implemented
                return null;
            case LECTURER:
                // Add LecturerDaoImpl when implemented
                return null;
            default:
                return null;
        }
    }
}
