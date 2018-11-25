package com.tangbaobao.spike.exception;

import com.tangbaobao.spike.controller.resp.ResultResp;
import com.tangbaobao.spike.vo.ResultVO;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangxuejun
 * @version 2018/10/10 4:28 PM
 */
@ControllerAdvice
@ResponseBody
public class GlobalException {
    @ExceptionHandler(value = Exception.class)
    public ResultVO<Object> exceptionHandler(HttpServletRequest request, Exception e) {
        String msg = null;
        if (e instanceof SkipException) {
            SkipException ge = (SkipException) e;
            msg = ge.exceptionEnums.getMsg();
        } else if (e instanceof BindException) {
            if (((BindException) e).hasErrors()) {
                FieldError fieldError = ((BindException) e).getFieldError();
                msg = fieldError.getDefaultMessage();
            }
        } else {
            msg = e.getMessage();
        }

        return ResultResp.ERROR(msg);
    }
}
