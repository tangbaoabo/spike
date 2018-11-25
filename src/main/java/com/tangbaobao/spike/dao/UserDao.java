package com.tangbaobao.spike.dao;

import com.tangbaobao.spike.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author tangxuejun
 * @version 2018/10/10 12:14 PM
 */
@Component
public interface UserDao {
    /**
     * 根据用户手机号查询用户信息
     *
     * @param phone
     * @return
     */
    User getUserByPhone(@Param("phone") String phone);
}
