package com.service;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 */
public interface IStudentService<T> {
    public List<T> getall()throws Exception;
}
