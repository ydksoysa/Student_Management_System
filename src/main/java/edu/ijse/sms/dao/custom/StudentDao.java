package edu.ijse.sms.dao.custom;

import edu.ijse.sms.dao.CrudDao;
import edu.ijse.sms.entity.StudentEntity;

import java.util.List;

public interface StudentDao extends CrudDao<StudentEntity, String> {
    boolean addStudent(StudentEntity student) throws Exception;
    boolean updateStudent(StudentEntity student) throws Exception;
    boolean deleteStudent(String registeredId) throws Exception;
    StudentEntity findStudent(String registeredId) throws Exception;
    List<StudentEntity> getAllStudents() throws Exception;
}
