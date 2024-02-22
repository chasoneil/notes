package com.chason.proxy.example03;

/**
 * 目标类 （代理者即将代理的类）
 */
public class UserServiceImpl {

    public void selectUser () {
        System.out.println("查询用户信息");
    }

}
