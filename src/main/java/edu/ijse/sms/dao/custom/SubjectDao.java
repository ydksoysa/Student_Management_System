package edu.ijse.sms.dao.custom;

import edu.ijse.sms.dao.CrudDao;
import edu.ijse.sms.entity.SubjectEntity;

import java.util.List;

public interface SubjectDao extends CrudDao<SubjectEntity, String> {
    boolean addSubject(SubjectEntity subject) throws Exception;
    boolean updateSubject(SubjectEntity subject) throws Exception;
    boolean deleteSubject(String subjectId) throws Exception;
    SubjectEntity findSubject(String subjectId) throws Exception;
    List<SubjectEntity> getAllSubjects() throws Exception;
}
