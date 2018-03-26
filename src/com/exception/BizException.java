package com.exception;

import org.springframework.dao.DataAccessException;

/**
 * Created by Administrator on 2017/1/23.
 */
public class BizException extends DataAccessException {
    public BizException(String msg) {
        super(msg);
    }

    public BizException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
