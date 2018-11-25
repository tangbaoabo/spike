package com.tangbaobao.spike.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DaoTestTest {
    @Autowired
    private UserDao userDao;
    @Test

    public void getName() {
        System.out.println(userDao.getUserByPhone("18845891051"));
    }


}