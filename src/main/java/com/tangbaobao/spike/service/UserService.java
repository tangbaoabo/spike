package com.tangbaobao.spike.service;

import com.tangbaobao.spike.domain.User;

import java.util.Optional;

/**
 * @author tangxuejun
 * @version 2018/10/10 12:31 PM
 */
public interface UserService {
    Optional<User> getUserByPhone(String phone);
}
