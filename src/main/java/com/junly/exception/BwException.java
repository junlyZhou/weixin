package com.junly.exception;

import com.junly.enums.ResultEnum;

/**
 * @Author: Administrator
 * @Date: 2019/10/20 0020 13:38
 * @Description:
 */
public class BwException extends RuntimeException{

    private Integer code;

    public BwException(ResultEnum resultEnum){
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public BwException(Integer code, String message){
        super(message);
        this.code = code;
    }

}
