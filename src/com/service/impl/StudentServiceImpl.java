package com.service.impl;

import com.dao.StudentDao;
import com.dao.impl.StudentDaoImpl;
import com.pojo.Student;
import com.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 */
@Service
public class StudentServiceImpl<T extends Student> implements IStudentService<T> {
    @Autowired
    private StudentDaoImpl<T> dao;
    @Override
    public List<T> getall() throws Exception {
        return dao.getList();
    }
}
