package com.mrdios.back.student.service.impl;

import com.billiegen.common.framework.service.BaseServiceImpl;
import com.mrdios.back.student.dao.StudentDao;
import com.mrdios.back.student.entity.Student;
import com.mrdios.back.student.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author CodePorter
 * @date 2017-11-22
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<StudentDao, Student, String> implements StudentService {
}
