package edu.ijse.sms.service.custom;

import edu.ijse.sms.dto.SubjectDto;
import edu.ijse.sms.service.SuperService;

import java.util.List;

public interface SubjectService extends SuperService {
    boolean addSubject(SubjectDto subjectDto) throws Exception;
    boolean updateSubject(SubjectDto subjectDto) throws Exception;
    boolean deleteSubject(String subjectId) throws Exception;
    SubjectDto findSubject(String subjectId) throws Exception;
    List<SubjectDto> getAllSubjects() throws Exception;
}
