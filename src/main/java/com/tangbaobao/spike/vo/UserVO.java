package com.tangbaobao.spike.vo;

import com.tangbaobao.spike.constant.ValidateConfig;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @author tangxuejun
 * @version 2018/10/10 12:28 PM
 */
@Getter
@Setter
@ToString
public class UserVO {
    @NotEmpty(message = "手机号不能为空")
    @Pattern(regexp = ValidateConfig.PHONE_NUMBER_REG, message = "手机号格式不正确")
    private String phone;
    @NotBlank(message = "密码不能为空")
    private String password;
}
