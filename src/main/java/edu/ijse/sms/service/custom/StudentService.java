package edu.ijse.sms.service.custom;

import edu.ijse.sms.dto.StudentDto;
import edu.ijse.sms.service.SuperService;
import java.util.List;

public interface StudentService {
    boolean addStudent(StudentDto studentDto) throws Exception;
    boolean updateStudent(StudentDto studentDto) throws Exception;
    boolean deleteStudent(String registeredId) throws Exception;
    StudentDto findStudent(String registeredId) throws Exception;
    List<StudentDto> getAllStudents() throws Exception;
}
