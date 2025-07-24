package edu.ijse.sms.service.impl;

import edu.ijse.sms.dao.DaoFactory;
import edu.ijse.sms.dao.custom.AttendanceDao;
import edu.ijse.sms.dto.AttendanceDto;
import edu.ijse.sms.entity.AttendanceEntity;
import edu.ijse.sms.service.custom.AttendanceService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class AttendanceServiceImpl implements AttendanceService {
    private final AttendanceDao attendanceDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ATTENDANCE);

    @Override
    public boolean addAttendance(AttendanceDto attendanceDto) throws Exception {
        AttendanceEntity entity = new AttendanceEntity(
                attendanceDto.getDate(),
                attendanceDto.getSubjectId(),
                attendanceDto.getStudentId(),
                attendanceDto.getStatus()
        );
        return attendanceDao.addAttendance(entity);
    }

    @Override
    public boolean updateAttendance(AttendanceDto attendanceDto) throws Exception {
        AttendanceEntity entity = new AttendanceEntity(
                attendanceDto.getDate(),
                attendanceDto.getSubjectId(),
                attendanceDto.getStudentId(),
                attendanceDto.getStatus()
        );
        return attendanceDao.updateAttendance(entity);
    }

    @Override
    public boolean deleteAttendance(int attendanceId) throws Exception {
        return attendanceDao.deleteAttendance(attendanceId);
    }

    @Override
    public AttendanceDto findAttendance(int attendanceId) throws Exception {
        AttendanceEntity entity = attendanceDao.findAttendance(attendanceId);
        if (entity != null) {
            return new AttendanceDto(
                    entity.getDate(),
                    entity.getSubjectId(),
                    entity.getStudentId(),
                    entity.getStatus()
            );
        }
        return null;
    }

    @Override
    public List<AttendanceDto> getAllAttendance() throws Exception {
        List<AttendanceEntity> entityList = attendanceDao.getAllAttendance();
        List<AttendanceDto> dtoList = new ArrayList<>();

        for (AttendanceEntity entity : entityList) {
            dtoList.add(new AttendanceDto(
                    entity.getDate(),
                    entity.getSubjectId(),
                    entity.getStudentId(),
                    entity.getStatus()
            ));
        }
        return dtoList;
    }

    @Override
    public List<AttendanceDto> getAttendanceByDateAndSubject(Date date, String subjectId) throws Exception {
        List<AttendanceEntity> entityList = attendanceDao.getAttendanceByDateAndSubject(date, subjectId);
        List<AttendanceDto> dtoList = new ArrayList<>();

        for (AttendanceEntity entity : entityList) {
            dtoList.add(new AttendanceDto(
                    entity.getDate(),
                    entity.getSubjectId(),
                    entity.getStudentId(),
                    entity.getStatus()
            ));
        }
        return dtoList;
    }

    @Override
    public boolean markAttendance(Date date, String subjectId, String studentId, String status) throws Exception {
        return attendanceDao.markAttendance(date, subjectId, studentId, status);
    }
}
