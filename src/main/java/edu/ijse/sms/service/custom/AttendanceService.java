package edu.ijse.sms.service.custom;

import edu.ijse.sms.dto.AttendanceDto;
import edu.ijse.sms.service.SuperService;

import java.sql.Date;
import java.util.List;

public interface AttendanceService extends SuperService {
    boolean addAttendance(AttendanceDto attendanceDto) throws Exception;
    boolean updateAttendance(AttendanceDto attendanceDto) throws Exception;
    boolean deleteAttendance(int attendanceId) throws Exception;
    AttendanceDto findAttendance(int attendanceId) throws Exception;
    List<AttendanceDto> getAllAttendance() throws Exception;
    List<AttendanceDto> getAttendanceByDateAndSubject(Date date, String subjectId) throws Exception;
    boolean markAttendance(Date date, String subjectId, String studentId, String status) throws Exception;
}
