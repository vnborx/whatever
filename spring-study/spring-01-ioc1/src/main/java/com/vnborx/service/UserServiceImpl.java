package com.vnborx.service;

import com.vnborx.dao.UserDao;
import com.vnborx.dao.UserDaoImpl;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public void getUser() {
        userDao.getUser();
    }
}
