package edu.ijse.sms.service.custom;

import edu.ijse.sms.dto.CourseDto;
import edu.ijse.sms.service.SuperService;

import java.util.List;

public interface CourseService extends SuperService {
    boolean addCourse(CourseDto courseDto) throws Exception;
    boolean updateCourse(CourseDto courseDto) throws Exception;
    boolean deleteCourse(String courseId) throws Exception;
    CourseDto findCourse(String courseId) throws Exception;
    List<CourseDto> getAllCourses() throws Exception;
    boolean addSubjectToCourse(String courseId, String subjectId) throws Exception;
    boolean removeSubjectFromCourse(String courseId, String subjectId) throws Exception;
    List<String> getSubjectsForCourse(String courseId) throws Exception;
}
