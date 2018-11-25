package com.tangbaobao.spike.controller.resp;

import com.tangbaobao.spike.vo.ResultVO;

/**
 * @author tangxuejun
 * @version 2018/10/10 10:46 AM
 */
public class ResultResp {
    public static ResultVO<Object> SUCCESS(Object object) {
        return new ResultVO<>(0, "success", object);
    }

    public static ResultVO<Object> SUCCESS() {
        return SUCCESS(null);
    }

    public static ResultVO<Object> ERROR() {
        return new ResultVO<>(1, "error", null);
    }

    public static ResultVO<Object> ERROR(String msg) {
        return new ResultVO<>(1, msg, null);
    }
}
