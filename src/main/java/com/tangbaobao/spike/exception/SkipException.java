package com.tangbaobao.spike.exception;

import com.tangbaobao.spike.enums.ExceptionEnums;
import lombok.Getter;

/**
 * @author tangxuejun
 * @version 2018/10/10 12:50 PM
 */
@Getter
public class SkipException extends Exception {
    protected final ExceptionEnums exceptionEnums;

    public SkipException(ExceptionEnums exceptionEnums) {
        super(exceptionEnums.getMsg());
        this.exceptionEnums = exceptionEnums;
    }

    public SkipException(String message, ExceptionEnums exceptionEnums) {
        super(message);
        this.exceptionEnums = exceptionEnums;
    }

    public SkipException(String message, Throwable cause, ExceptionEnums exceptionEnums) {
        super(message, cause);
        this.exceptionEnums = exceptionEnums;
    }

    public SkipException(Throwable cause, ExceptionEnums exceptionEnums) {
        super(cause);
        this.exceptionEnums = exceptionEnums;
    }
}
