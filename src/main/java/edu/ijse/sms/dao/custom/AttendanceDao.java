package edu.ijse.sms.dao.custom;

import edu.ijse.sms.dao.CrudDao;
import edu.ijse.sms.entity.AttendanceEntity;

import java.sql.Date;
import java.util.List;

public interface AttendanceDao extends CrudDao<AttendanceEntity, Integer> {
    boolean addAttendance(AttendanceEntity attendance) throws Exception;
    boolean updateAttendance(AttendanceEntity attendance) throws Exception;
    boolean deleteAttendance(int attendanceId) throws Exception;
    AttendanceEntity findAttendance(int attendanceId) throws Exception;
    List<AttendanceEntity> getAllAttendance() throws Exception;
    List<AttendanceEntity> getAttendanceByDateAndSubject(Date date, String subjectId) throws Exception;
    boolean markAttendance(Date date, String subjectId, String studentId, String status) throws Exception;
}
