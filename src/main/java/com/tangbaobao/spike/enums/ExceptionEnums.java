package com.tangbaobao.spike.enums;

import lombok.Getter;

/**
 * @author tangxuejun
 * @version 2018/10/10 12:52 PM
 */
@Getter
public enum ExceptionEnums {
    //1000-2000用户相关错误
    USER_NOT_EXISTS(1000, "用户不存在"),
    AUTH_FAIL(1001, "用户名或密码错误"),
    //2001-3000秒杀相关
    SPIKE_PRODUCT_OVER(2001, "商品库存不足"),
    SPIKE_PRODUCT_REPEAT(2002, "不能重复秒杀"),
    ;


    private int code;
    private String msg;

    ExceptionEnums(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
