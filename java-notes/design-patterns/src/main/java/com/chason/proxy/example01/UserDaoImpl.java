package com.chason.proxy.example01;

public class UserDaoImpl implements IUserDao {

    @Override
    public void save() {
        System.out.println("save user data");
    }

}
