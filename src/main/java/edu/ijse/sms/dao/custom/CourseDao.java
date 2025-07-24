package edu.ijse.sms.dao.custom;

import edu.ijse.sms.dao.CrudDao;
import edu.ijse.sms.entity.CourseEntity;

import java.util.List;

public interface CourseDao extends CrudDao<CourseEntity, String> {
    boolean addCourse(CourseEntity course) throws Exception;
    boolean updateCourse(CourseEntity course) throws Exception;
    boolean deleteCourse(String courseId) throws Exception;
    CourseEntity findCourse(String courseId) throws Exception;
    List<CourseEntity> getAllCourses() throws Exception;
    boolean addSubjectToCourse(String courseId, String subjectId) throws Exception;
    boolean removeSubjectFromCourse(String courseId, String subjectId) throws Exception;
    List<String> getSubjectsForCourse(String courseId) throws Exception;
}
