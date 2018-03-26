package com.dao;

import java.util.List;

/**
 * Created by Administrator on 2017/1/23.
 */
public interface StudentDao<T> {
    public List<T> getList()throws Exception;
}
