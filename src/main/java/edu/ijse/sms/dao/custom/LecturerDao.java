package edu.ijse.sms.dao.custom;

import edu.ijse.sms.dao.CrudDao;
import edu.ijse.sms.entity.LecturerEntity;

import java.util.List;

public interface LecturerDao extends CrudDao<LecturerEntity, String> {
    boolean addLecturer(LecturerEntity lecturer) throws Exception;
    boolean updateLecturer(LecturerEntity lecturer) throws Exception;
    boolean deleteLecturer(String lecturerId) throws Exception;
    LecturerEntity findLecturer(String lecturerId) throws Exception;
    List<LecturerEntity> getAllLecturers() throws Exception;
    boolean addSubjectToLecturer(String lecturerId, String subjectId) throws Exception;
    boolean removeSubjectFromLecturer(String lecturerId, String subjectId) throws Exception;
    List<String> getSubjectsForLecturer(String lecturerId) throws Exception;
}
