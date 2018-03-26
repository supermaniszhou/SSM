package com.mapper;

import com.pojo.Student;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 */

public interface StudentMapper extends SqlMapper {
    public List<Student> getAll();
}
