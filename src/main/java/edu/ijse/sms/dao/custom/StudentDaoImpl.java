package edu.ijse.sms.dao.custom;

import edu.ijse.sms.dao.SuperDao;
import edu.ijse.sms.db.DBConnection;
import edu.ijse.sms.entity.StudentEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDaoImpl implements StudentDao {
    @Override
    public boolean addStudent(StudentEntity student) throws Exception {
        String sql = "INSERT INTO student (reg_id, name, course, contact_num) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, student.getRegId());
            pstm.setString(2, student.getName());
            pstm.setString(3, student.getCourse());
            pstm.setString(4, student.getContactNum());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateStudent(StudentEntity student) throws Exception {
        String sql = "UPDATE student SET name=?, course=?, contact_num=? WHERE reg_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, student.getName());
            pstm.setString(2, student.getCourse());
            pstm.setString(3, student.getContactNum());
            pstm.setString(4, student.getRegId());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteStudent(String registeredId) throws Exception {
        String sql = "DELETE FROM student WHERE reg_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, registeredId);
            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public StudentEntity findStudent(String registeredId) throws Exception {
        String sql = "SELECT * FROM student WHERE reg_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, registeredId);
            ResultSet rst = pstm.executeQuery();

            if (rst.next()) {
                return new StudentEntity(
                        rst.getInt("id"),
                        rst.getString("reg_id"),
                        rst.getString("name"),
                        rst.getString("course"),
                        rst.getString("contact_num")
                );
            }
            return null;
        }
    }

    @Override
    public List<StudentEntity> getAllStudents() throws Exception {
        String sql = "SELECT * FROM student";
        List<StudentEntity> studentList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {

            while (rst.next()) {
                studentList.add(new StudentEntity(
                        rst.getInt("id"),
                        rst.getString("reg_id"),
                        rst.getString("name"),
                        rst.getString("course"),
                        rst.getString("contact_num")
                ));
            }
            return studentList;
        }
    }

    @Override
    public boolean add(StudentEntity entity) throws Exception {
        return addStudent(entity);
    }

    @Override
    public boolean update(StudentEntity entity) throws Exception {
        return updateStudent(entity);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return deleteStudent(id);
    }

    @Override
    public StudentEntity find(String id) throws Exception {
        return findStudent(id);
    }

    @Override
    public List<StudentEntity> getAll() throws Exception {
        return getAllStudents();
    }
}
