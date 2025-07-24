package edu.ijse.sms.service.impl;
import edu.ijse.sms.dao.custom.StudentDao;
import edu.ijse.sms.dao.DaoFactory;

import edu.ijse.sms.service.custom.StudentService;
import edu.ijse.sms.entity.StudentEntity;
import edu.ijse.sms.dto.StudentDto;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {
    private DaoFactory DAOFactory;
    private final StudentDao studentDao = (StudentDao) DaoFactory.getInstance().getDao(DaoFactory.DAOType.STUDENT);

    @Override
    public boolean addStudent(StudentDto studentDto) throws Exception {
        StudentEntity entity = new StudentEntity();
        entity.setRegId(studentDto.getRegId());
        entity.setName(studentDto.getName());
        entity.setCourse(studentDto.getCourse());
        entity.setContactNum(studentDto.getContactNum());
        return studentDao.addStudent(entity);
    }

    @Override
    public boolean updateStudent(StudentDto studentDto) throws Exception {
        StudentEntity entity = new StudentEntity();
        entity.setRegId(studentDto.getRegId());
        entity.setName(studentDto.getName());
        entity.setCourse(studentDto.getCourse());
        entity.setContactNum(studentDto.getContactNum());
        return studentDao.updateStudent(entity);
    }

    @Override
    public boolean deleteStudent(String registeredId) throws Exception {
        return studentDao.deleteStudent(registeredId);
    }

    @Override
    public StudentDto findStudent(String registeredId) throws Exception {
        StudentEntity entity = studentDao.findStudent(registeredId);
        if (entity != null) {
            return new StudentDto(
                    entity.getRegId(),
                    entity.getName(),
                    entity.getCourse(),
                    entity.getContactNum()
            );
        }
        return null;
    }

    @Override
    public List<StudentDto> getAllStudents() throws Exception {
        List<StudentEntity> entityList = studentDao.getAllStudents();
        List<StudentDto> dtoList = new ArrayList<>(); // Initialize with empty list
        if (entityList != null) {
            for (StudentEntity entity : entityList) {
                dtoList.add(new StudentDto(
                        entity.getRegId(),
                        entity.getName(),
                        entity.getCourse(),
                        entity.getContactNum()
                ));
            }
        }
        return dtoList;

    }
}
