package com.net.netty.bio.class02.pre;

public class UserServiceImpl implements UserService {

    @Override
    public User findUserByID(Integer id) {
        return new User(id,"user_name_1");
    }

    @Override
    public User findUserByID2(Integer id) {
        return new User(id,"user_name_2");
    }

}
