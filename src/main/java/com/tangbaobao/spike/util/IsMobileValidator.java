package com.tangbaobao.spike.util;

import com.tangbaobao.spike.annotation.IsMobile;
import com.tangbaobao.spike.constant.ValidateConfig;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author tangxuejun
 * @version 2018/10/10 3:56 PM
 * 校验手机号注解
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;


    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return value.matches(ValidateConfig.PHONE_NUMBER_REG);
        }
        return true;
    }
}
