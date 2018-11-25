package com.tangbaobao.spike.service.impl;

import com.tangbaobao.spike.dao.UserDao;
import com.tangbaobao.spike.domain.User;
import com.tangbaobao.spike.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author tangxuejun
 * @version 2018/10/10 12:38 PM
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }


    @Override
    public Optional<User> getUserByPhone(String phone) {
        return Optional.ofNullable(userDao.getUserByPhone(phone));
    }


}
