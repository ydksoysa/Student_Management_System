package edu.ijse.sms.service.custom;

import edu.ijse.sms.dto.LecturerDto;
import edu.ijse.sms.service.SuperService;

import java.util.List;

public interface LecturerService extends SuperService {
    boolean addLecturer(LecturerDto lecturerDto) throws Exception;
    boolean updateLecturer(LecturerDto lecturerDto) throws Exception;
    boolean deleteLecturer(String lecturerId) throws Exception;
    LecturerDto findLecturer(String lecturerId) throws Exception;
    List<LecturerDto> getAllLecturers() throws Exception;
    boolean addSubjectToLecturer(String lecturerId, String subjectId) throws Exception;
    boolean removeSubjectFromLecturer(String lecturerId, String subjectId) throws Exception;
    List<String> getSubjectsForLecturer(String lecturerId) throws Exception;
}
