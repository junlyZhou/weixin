package com.junly.enums;

import lombok.Getter;

/**
 * @Author: Administrator
 * @Date: 2019/10/20 0020 13:40
 * @Description:
 */
@Getter
public enum ResultEnum {

    SUCCESS(0,"成功"),

    PARAM_ERROR(1,"参数不正确"),

    WECHAT_MP_ERROR(20,"微信公众账号方面错误"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
