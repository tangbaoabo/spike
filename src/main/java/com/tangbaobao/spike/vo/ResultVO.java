package com.tangbaobao.spike.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author tangxuejun
 * @version 2018/10/10 10:41 AM
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultVO<T> {
    /**
     * 状态码 0位正常,1位错误
     */
    private Integer code;
    /**
     * 状态信息
     */
    private String msg;
    /**
     * 数据
     */
    private T data;
}
