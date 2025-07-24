package edu.ijse.sms.service.impl;

import edu.ijse.sms.dao.DaoFactory;
import edu.ijse.sms.dao.custom.SubjectDao;
import edu.ijse.sms.dto.SubjectDto;
import edu.ijse.sms.entity.SubjectEntity;
import edu.ijse.sms.service.custom.SubjectService;

import java.util.ArrayList;
import java.util.List;

public class SubjectServiceImpl implements SubjectService {
    private final SubjectDao subjectDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.SUBJECT);

    @Override
    public boolean addSubject(SubjectDto subjectDto) throws Exception {
        SubjectEntity entity = new SubjectEntity(
                subjectDto.getSubjectId(),
                subjectDto.getSubjectName()
        );
        return subjectDao.addSubject(entity);
    }

    @Override
    public boolean updateSubject(SubjectDto subjectDto) throws Exception {
        SubjectEntity entity = new SubjectEntity(
                subjectDto.getSubjectId(),
                subjectDto.getSubjectName()
        );
        return subjectDao.updateSubject(entity);
    }

    @Override
    public boolean deleteSubject(String subjectId) throws Exception {
        return subjectDao.deleteSubject(subjectId);
    }

    @Override
    public SubjectDto findSubject(String subjectId) throws Exception {
        SubjectEntity entity = subjectDao.findSubject(subjectId);
        if (entity != null) {
            return new SubjectDto(
                    entity.getSubjectId(),
                    entity.getSubjectName()
            );
        }
        return null;
    }

    @Override
    public List<SubjectDto> getAllSubjects() throws Exception {
        List<SubjectEntity> entityList = subjectDao.getAllSubjects();
        List<SubjectDto> dtoList = new ArrayList<>();

        for (SubjectEntity entity : entityList) {
            dtoList.add(new SubjectDto(
                    entity.getSubjectId(),
                    entity.getSubjectName()
            ));
        }
        return dtoList;
    }
}