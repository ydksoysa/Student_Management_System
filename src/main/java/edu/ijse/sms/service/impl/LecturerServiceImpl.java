package edu.ijse.sms.service.impl;

import edu.ijse.sms.dao.DaoFactory;
import edu.ijse.sms.dao.custom.LecturerDao;
import edu.ijse.sms.dto.LecturerDto;
import edu.ijse.sms.entity.LecturerEntity;
import edu.ijse.sms.service.custom.LecturerService;

import java.util.ArrayList;
import java.util.List;

public class LecturerServiceImpl implements LecturerService {
    private final LecturerDao lecturerDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.LECTURER);

    @Override
    public boolean addLecturer(LecturerDto lecturerDto) throws Exception {
        LecturerEntity entity = new LecturerEntity(
                lecturerDto.getLecturerId(),
                lecturerDto.getLecturerName()
        );
        return lecturerDao.addLecturer(entity);
    }

    @Override
    public boolean updateLecturer(LecturerDto lecturerDto) throws Exception {
        LecturerEntity entity = new LecturerEntity(
                lecturerDto.getLecturerId(),
                lecturerDto.getLecturerName()
        );
        return lecturerDao.updateLecturer(entity);
    }

    @Override
    public boolean deleteLecturer(String lecturerId) throws Exception {
        return lecturerDao.deleteLecturer(lecturerId);
    }

    @Override
    public LecturerDto findLecturer(String lecturerId) throws Exception {
        LecturerEntity entity = lecturerDao.findLecturer(lecturerId);
        if (entity != null) {
            LecturerDto dto = new LecturerDto(
                    entity.getLecturerId(),
                    entity.getLecturerName()
            );
            dto.setSubjectIds(entity.getSubjectIds());
            return dto;
        }
        return null;
    }

    @Override
    public List<LecturerDto> getAllLecturers() throws Exception {
        List<LecturerEntity> entityList = lecturerDao.getAllLecturers();
        List<LecturerDto> dtoList = new ArrayList<>();

        for (LecturerEntity entity : entityList) {
            LecturerDto dto = new LecturerDto(
                    entity.getLecturerId(),
                    entity.getLecturerName()
            );
            dto.setSubjectIds(entity.getSubjectIds());
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public boolean addSubjectToLecturer(String lecturerId, String subjectId) throws Exception {
        return lecturerDao.addSubjectToLecturer(lecturerId, subjectId);
    }

    @Override
    public boolean removeSubjectFromLecturer(String lecturerId, String subjectId) throws Exception {
        return lecturerDao.removeSubjectFromLecturer(lecturerId, subjectId);
    }

    @Override
    public List<String> getSubjectsForLecturer(String lecturerId) throws Exception {
        return lecturerDao.getSubjectsForLecturer(lecturerId);
    }
}
