package edu.ijse.sms.service.impl;

import edu.ijse.sms.dao.DaoFactory;
import edu.ijse.sms.dao.custom.CourseDao;
import edu.ijse.sms.dto.CourseDto;
import edu.ijse.sms.dto.SubjectDto;
import edu.ijse.sms.entity.CourseEntity;
import edu.ijse.sms.service.custom.CourseService;
import edu.ijse.sms.service.custom.SubjectService;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {
    private final CourseDao courseDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.COURSE);
    private final SubjectService subjectService = new SubjectServiceImpl();

    @Override
    public boolean addCourse(CourseDto courseDto) throws Exception {
        CourseEntity entity = new CourseEntity(
                courseDto.getCourseId(),
                courseDto.getCourseName()
        );
        return courseDao.addCourse(entity);
    }

    @Override
    public boolean updateCourse(CourseDto courseDto) throws Exception {
        CourseEntity entity = new CourseEntity(
                courseDto.getCourseId(),
                courseDto.getCourseName()
        );
        return courseDao.updateCourse(entity);
    }

    @Override
    public boolean deleteCourse(String courseId) throws Exception {
        return courseDao.deleteCourse(courseId);
    }

    @Override
    public CourseDto findCourse(String courseId) throws Exception {
        CourseEntity entity = courseDao.findCourse(courseId);
        if (entity != null) {
            CourseDto dto = new CourseDto(
                    entity.getCourseId(),
                    entity.getCourseName()
            );
            dto.setSubjectIds(entity.getSubjectIds());

            // Load subject names
            List<String> subjectNames = new ArrayList<>();
            for (String subjectId : entity.getSubjectIds()) {
                SubjectDto subject = subjectService.findSubject(subjectId);
                if (subject != null) {
                    subjectNames.add(subject.getSubjectName());
                }
            }
            dto.setSubjectNames(subjectNames);

            return dto;
        }
        return null;
    }

    @Override
    public List<CourseDto> getAllCourses() throws Exception {
        List<CourseEntity> entityList = courseDao.getAllCourses();
        List<CourseDto> dtoList = new ArrayList<>();

        for (CourseEntity entity : entityList) {
            CourseDto dto = new CourseDto(
                    entity.getCourseId(),
                    entity.getCourseName()
            );
            dto.setSubjectIds(entity.getSubjectIds());

            // Load subject names
            List<String> subjectNames = new ArrayList<>();
            for (String subjectId : entity.getSubjectIds()) {
                SubjectDto subject = subjectService.findSubject(subjectId);
                if (subject != null) {
                    subjectNames.add(subject.getSubjectName());
                }
            }
            dto.setSubjectNames(subjectNames);

            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean addSubjectToCourse(String courseId, String subjectId) throws Exception {
        return courseDao.addSubjectToCourse(courseId, subjectId);
    }

    @Override
    public boolean removeSubjectFromCourse(String courseId, String subjectId) throws Exception {
        return courseDao.removeSubjectFromCourse(courseId, subjectId);
    }

    @Override
    public List<String> getSubjectsForCourse(String courseId) throws Exception {
        return courseDao.getSubjectsForCourse(courseId);
    }
}
