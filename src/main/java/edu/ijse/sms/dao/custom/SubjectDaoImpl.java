package edu.ijse.sms.dao.custom;

import edu.ijse.sms.dao.SuperDao;
import edu.ijse.sms.db.DBConnection;
import edu.ijse.sms.entity.SubjectEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SubjectDaoImpl implements SubjectDao {
    @Override
    public boolean addSubject(SubjectEntity subject) throws Exception {
        String sql = "INSERT INTO subject (subject_id, subject_name) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, subject.getSubjectId());
            pstm.setString(2, subject.getSubjectName());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean updateSubject(SubjectEntity subject) throws Exception {
        String sql = "UPDATE subject SET subject_name=? WHERE subject_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, subject.getSubjectName());
            pstm.setString(2, subject.getSubjectId());

            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public boolean deleteSubject(String subjectId) throws Exception {
        String sql = "DELETE FROM subject WHERE subject_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, subjectId);
            return pstm.executeUpdate() > 0;
        }
    }

    @Override
    public SubjectEntity findSubject(String subjectId) throws Exception {
        String sql = "SELECT * FROM subject WHERE subject_id=?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, subjectId);
            ResultSet rst = pstm.executeQuery();

            if (rst.next()) {
                return new SubjectEntity(
                        rst.getString("subject_id"),
                        rst.getString("subject_name")
                );
            }
            return null;
        }
    }

    @Override
    public List<SubjectEntity> getAllSubjects() throws Exception {
        String sql = "SELECT * FROM subject";
        List<SubjectEntity> subjectList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement pstm = connection.prepareStatement(sql);
             ResultSet rst = pstm.executeQuery()) {

            while (rst.next()) {
                subjectList.add(new SubjectEntity(
                        rst.getString("subject_id"),
                        rst.getString("subject_name")
                ));
            }
            return subjectList;
        }
    }

    // Implement CrudDao methods
    @Override
    public boolean add(SubjectEntity entity) throws Exception {
        return addSubject(entity);
    }

    @Override
    public boolean update(SubjectEntity entity) throws Exception {
        return updateSubject(entity);
    }

    @Override
    public boolean delete(String id) throws Exception {
        return deleteSubject(id);
    }

    @Override
    public SubjectEntity find(String id) throws Exception {
        return findSubject(id);
    }

    @Override
    public List<SubjectEntity> getAll() throws Exception {
        return getAllSubjects();
    }
}