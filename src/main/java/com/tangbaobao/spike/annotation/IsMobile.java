package com.tangbaobao.spike.annotation;

import com.tangbaobao.spike.util.IsMobileValidator;

import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {IsMobileValidator.class})

public @interface IsMobile {
    boolean required() default false;

    String message() default "手机号码格式错误";
}
