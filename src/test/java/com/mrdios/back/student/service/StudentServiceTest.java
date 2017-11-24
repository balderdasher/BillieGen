package com.mrdios.back.student.service;

import com.Application;
import com.billiegen.system.enums.Sex;
import com.mrdios.back.student.entity.Student;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

/**
 * @author CodePorter
 * @date 2017-11-22
 */
@Transactional
@Rollback(value = false)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentBetaService studentBetaService;

    private Student student;

    @Before
    public void setUp() {
        student = new Student();
        student.setName("tom");
        student.setAge(22);
        student.setSex(Sex.MALE);
    }

    @Test
    public void save() {
        studentService.save(student);
    }

    @Test
    public void betaSave() {
        studentBetaService.save(student);
    }
}