package edu.ijse.sms.dao.custom;

import edu.ijse.sms.dao.SuperDao;
import edu.ijse.sms.db.DBConnection;
import edu.ijse.sms.entity.CourseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    @Override
    public boolean addCourse(CourseEntity course) throws Exception {
        String sql = "INSERT INTO course (course_id, course_name) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, course.getCourseId());
            pstm.setString(2, course.getCourseName());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateCourse(CourseEntity course) throws Exception {
        String sql = "UPDATE course SET course_name=? WHERE course_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, course.getCourseName());
            pstm.setString(2, course.getCourseId());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteCourse(String courseId) throws Exception {
        // First delete from course_subject table
        String deleteMappingSql = "DELETE FROM course_subject WHERE course_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(deleteMappingSql)) {

            pstm.setString(1, courseId);
            pstm.executeUpdate();
        }

        // Then delete from course table
        String deleteCourseSql = "DELETE FROM course WHERE course_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(deleteCourseSql)) {

            pstm.setString(1, courseId);
            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public CourseEntity findCourse(String courseId) throws Exception {
        String sql = "SELECT * FROM course WHERE course_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, courseId);
            ResultSet rst = pstm.executeQuery();

            if (rst.next()) {
                CourseEntity course = new CourseEntity(
                        rst.getString("course_id"),
                        rst.getString("course_name")
                );
                // Load subjects for this course
                course.setSubjectIds(getSubjectsForCourse(courseId));
                return course;
            }
            return null;
        }
    }

    @Override
    public List<CourseEntity> getAllCourses() throws Exception {
        String sql = "SELECT * FROM course";
        List<CourseEntity> courseList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {

            while (rst.next()) {
                String courseId = rst.getString("course_id");
                CourseEntity course = new CourseEntity(
                        courseId,
                        rst.getString("course_name")
                );
                // Load subjects for each course
                course.setSubjectIds(getSubjectsForCourse(courseId));
                courseList.add(course);
            }
            return courseList;
        }
    }

    @Override
    public boolean addSubjectToCourse(String courseId, String subjectId) throws Exception {
        String sql = "INSERT INTO course_subject (course_id, subject_id) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, courseId);
            pstm.setString(2, subjectId);

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean removeSubjectFromCourse(String courseId, String subjectId) throws Exception {
        String sql = "DELETE FROM course_subject WHERE course_id=? AND subject_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, courseId);
            pstm.setString(2, subjectId);

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public List<String> getSubjectsForCourse(String courseId) throws Exception {
        String sql = "SELECT s.subject_id, s.subject_name FROM course_subject cs " +
                "JOIN subject s ON cs.subject_id = s.subject_id " +
                "WHERE cs.course_id=?";
        List<String> subjectIds = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, courseId);
            ResultSet rst = pstm.executeQuery();

            while (rst.next()) {
                subjectIds.add(rst.getString("subject_id"));
            }
            return subjectIds;
        }
    }

    // Implement CrudDao methods
    @Override
    public boolean add(CourseEntity entity) throws Exception {
        return addCourse(entity);
    }

    @Override
    public boolean update(CourseEntity entity) throws Exception {
        return updateCourse(entity);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return deleteCourse(id);
    }

    @Override
    public CourseEntity find(String id) throws Exception {
        return findCourse(id);
    }

    @Override
    public List<CourseEntity> getAll() throws Exception {
        return getAllCourses();
    }
}
