package com.dao.impl;

import com.dao.StudentDao;
import com.mapper.StudentMapper;
import com.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 */
@SuppressWarnings("unchecked")
@Repository
public class StudentDaoImpl<T extends Student> implements StudentDao<T>{
    @Autowired
    private StudentMapper mapper;
    @Override
    public List<T> getList() throws Exception {
        return (List<T>) mapper.getAll();
    }
}
