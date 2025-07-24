package edu.ijse.sms.dao.custom;

import edu.ijse.sms.dao.SuperDao;
import edu.ijse.sms.db.DBConnection;
import edu.ijse.sms.entity.LecturerEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LecturerDaoImpl implements LecturerDao {
    @Override
    public boolean addLecturer(LecturerEntity lecturer) throws Exception {
        String sql = "INSERT INTO lecturer (lecturer_id, lecturer_name) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, lecturer.getLecturerId());
            pstm.setString(2, lecturer.getLecturerName());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateLecturer(LecturerEntity lecturer) throws Exception {
        String sql = "UPDATE lecturer SET lecturer_name=? WHERE lecturer_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, lecturer.getLecturerName());
            pstm.setString(2, lecturer.getLecturerId());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteLecturer(String lecturerId) throws Exception {
        // First delete from lecturer_subject table
        String deleteMappingSql = "DELETE FROM lecturer_subject WHERE lecturer_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(deleteMappingSql)) {

            pstm.setString(1, lecturerId);
            pstm.executeUpdate();
        }

        // Then delete from lecturer table
        String deleteLecturerSql = "DELETE FROM lecturer WHERE lecturer_id=?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(deleteLecturerSql)) {

            pstm.setString(1, lecturerId);
            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public LecturerEntity findLecturer(String lecturerId) throws Exception {
        String sql = "SELECT * FROM lecturer WHERE lecturer_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, lecturerId);
            ResultSet rst = pstm.executeQuery();

            if (rst.next()) {
                LecturerEntity lecturer = new LecturerEntity(
                        rst.getString("lecturer_id"),
                        rst.getString("lecturer_name")
                );
                // Load subjects for this lecturer
                lecturer.setSubjectIds(getSubjectsForLecturer(lecturerId));
                return lecturer;
            }
            return null;
        }
    }

    @Override
    public List<LecturerEntity> getAllLecturers() throws Exception {
        String sql = "SELECT * FROM lecturer";
        List<LecturerEntity> lecturerList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {

            while (rst.next()) {
                String lecturerId = rst.getString("lecturer_id");
                LecturerEntity lecturer = new LecturerEntity(
                        lecturerId,
                        rst.getString("lecturer_name")
                );
                // Load subjects for each lecturer
                lecturer.setSubjectIds(getSubjectsForLecturer(lecturerId));
                lecturerList.add(lecturer);
            }
            return lecturerList;
        }
    }

    @Override
    public boolean addSubjectToLecturer(String lecturerId, String subjectId) throws Exception {
        String sql = "INSERT INTO lecturer_subject (lecturer_id, subject_id) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, lecturerId);
            pstm.setString(2, subjectId);

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean removeSubjectFromLecturer(String lecturerId, String subjectId) throws Exception {
        String sql = "DELETE FROM lecturer_subject WHERE lecturer_id=? AND subject_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, lecturerId);
            pstm.setString(2, subjectId);

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public List<String> getSubjectsForLecturer(String lecturerId) throws Exception {
        String sql = "SELECT subject_id FROM lecturer_subject WHERE lecturer_id=?";
        List<String> subjectIds = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, lecturerId);
            ResultSet rst = pstm.executeQuery();

            while (rst.next()) {
                subjectIds.add(rst.getString("subject_id"));
            }
            return subjectIds;
        }
    }

    // Implement CrudDao methods
    @Override
    public boolean add(LecturerEntity entity) throws Exception {
        return addLecturer(entity);
    }

    @Override
    public boolean update(LecturerEntity entity) throws Exception {
        return updateLecturer(entity);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return deleteLecturer(id);
    }

    @Override
    public LecturerEntity find(String id) throws Exception {
        return findLecturer(id);
    }

    @Override
    public List<LecturerEntity> getAll() throws Exception {
        return getAllLecturers();
    }
}