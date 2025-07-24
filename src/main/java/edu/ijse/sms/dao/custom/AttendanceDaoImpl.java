package edu.ijse.sms.dao.custom;

import edu.ijse.sms.dao.SuperDao;
import edu.ijse.sms.db.DBConnection;
import edu.ijse.sms.entity.AttendanceEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDaoImpl implements AttendanceDao {
    @Override
    public boolean addAttendance(AttendanceEntity attendance) throws Exception {
        String sql = "INSERT INTO attendance (date, subject_id, student_id, status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setDate(1, attendance.getDate());
            pstm.setString(2, attendance.getSubjectId());
            pstm.setString(3, attendance.getStudentId());
            pstm.setString(4, attendance.getStatus());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateAttendance(AttendanceEntity attendance) throws Exception {
        String sql = "UPDATE attendance SET status=? WHERE attendance_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, attendance.getStatus());
            pstm.setInt(2, attendance.getAttendanceId());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteAttendance(int attendanceId) throws Exception {
        String sql = "DELETE FROM attendance WHERE attendance_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setInt(1, attendanceId);
            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public AttendanceEntity findAttendance(int attendanceId) throws Exception {
        String sql = "SELECT * FROM attendance WHERE attendance_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setInt(1, attendanceId);
            ResultSet rst = pstm.executeQuery();

            if (rst.next()) {
                return new AttendanceEntity(
                        rst.getDate("date"),
                        rst.getString("subject_id"),
                        rst.getString("student_id"),
                        rst.getString("status")
                );
            }
            return null;
        }
    }

    @Override
    public List<AttendanceEntity> getAllAttendance() throws Exception {
        String sql = "SELECT * FROM attendance";
        List<AttendanceEntity> attendanceList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {

            while (rst.next()) {
                attendanceList.add(new AttendanceEntity(
                        rst.getDate("date"),
                        rst.getString("subject_id"),
                        rst.getString("student_id"),
                        rst.getString("status")
                ));
            }
            return attendanceList;
        }
    }

    @Override
    public List<AttendanceEntity> getAttendanceByDateAndSubject(Date date, String subjectId) throws Exception {
        String sql = "SELECT * FROM attendance WHERE date=? AND subject_id=?";
        List<AttendanceEntity> attendanceList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setDate(1, date);
            pstm.setString(2, subjectId);
            ResultSet rst = pstm.executeQuery();

            while (rst.next()) {
                attendanceList.add(new AttendanceEntity(
                        rst.getDate("date"),
                        rst.getString("subject_id"),
                        rst.getString("student_id"),
                        rst.getString("status")
                ));
            }
            return attendanceList;
        }
    }

    @Override
    public boolean markAttendance(Date date, String subjectId, String studentId, String status) throws Exception {
        // First check if attendance already exists for this student on this date and subject
        String checkSql = "SELECT * FROM attendance WHERE date=? AND subject_id=? AND student_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement checkPstm = connection.prepareStatement(checkSql)) {

            checkPstm.setDate(1, date);
            checkPstm.setString(2, subjectId);
            checkPstm.setString(3, studentId);
            ResultSet rst = checkPstm.executeQuery();

            if (rst.next()) {
                // Update existing record
                String updateSql = "UPDATE attendance SET status=? WHERE date=? AND subject_id=? AND student_id=?";
                try (PreparedStatement updatePstm = connection.prepareStatement(updateSql)) {
                    updatePstm.setString(1, status);
                    updatePstm.setDate(2, date);
                    updatePstm.setString(3, subjectId);
                    updatePstm.setString(4, studentId);
                    return updatePstm.executeUpdate() > 0;
                }
            } else {
                // Insert new record
                String insertSql = "INSERT INTO attendance (date, subject_id, student_id, status) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertPstm = connection.prepareStatement(insertSql)) {
                    insertPstm.setDate(1, date);
                    insertPstm.setString(2, subjectId);
                    insertPstm.setString(3, studentId);
                    insertPstm.setString(4, status);
                    return insertPstm.executeUpdate() > 0;
                }
            }
        }
    }

    // Implement CrudDao methods
    @Override
    public boolean add(AttendanceEntity entity) throws Exception {
        return addAttendance(entity);
    }

    @Override
    public boolean update(AttendanceEntity entity) throws Exception {
        return updateAttendance(entity);
    }

    @Override
    public boolean delete(Integer id) throws Exception {
        return deleteAttendance(id);
    }

    @Override
    public AttendanceEntity find(Integer id) throws Exception {
        return findAttendance(id);
    }

    @Override
    public List<AttendanceEntity> getAll() throws Exception {
        return getAllAttendance();
    }
}
