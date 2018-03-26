package com.test;

import com.mapper.StudentMapper;
import com.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 */
@ContextConfiguration(locations = {"classpath:app*.xml"})
public class StudentTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private StudentMapper mapper;
    @Test
    public void get(){
        List<Student> list=mapper.getAll();
        for (Student s:list
             ) {
            System.out.println(s.getName());
        }
    }
}
