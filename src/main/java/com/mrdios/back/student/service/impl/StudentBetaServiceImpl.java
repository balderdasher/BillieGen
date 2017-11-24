package com.mrdios.back.student.service.impl;

import com.mrdios.back.student.dao.StudentDao;
import com.mrdios.back.student.entity.Student;
import com.mrdios.back.student.service.StudentBetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CodePorter
 * @date 2017-11-22
 */
@Service
public class StudentBetaServiceImpl implements StudentBetaService {
    @Autowired
    private StudentDao studentDao;

    @Override
    @Transactional(readOnly = false)
    public void save(Student student) {
        studentDao.save(student);
    }
}
