package com.tangbaobao.spike.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @author tangxuejun
 * @version 2018/10/10 10:36 AM
 */
@Getter
@Setter
@ToString
public class User {
    private Long id;
    /**用户手机号*/
    private String phone;
    /**用户昵称*/
    private String nickName;
    /**密码*/
    private String password;
    /**密码偏移*/
    private String salt;
    /**用户头像*/
    private String userPic;
    /**注册时间*/
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime registerDate;
    /**上次登录时间*/
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime lastLoginDate;
    /**登录统计*/
    private Integer loginCount;
}
