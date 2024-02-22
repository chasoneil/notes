package com.chason.proxy.example01;

public class UserProxy implements IUserDao {

    private IUserDao userDao;

    public UserProxy (IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void save() {
        System.out.println("before save user, do something...");
        userDao.save();
        System.out.println("after save user, do something...");
    }
}
