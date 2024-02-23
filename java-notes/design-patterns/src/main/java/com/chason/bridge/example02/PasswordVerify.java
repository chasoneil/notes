package com.chason.bridge.example02;

public class PasswordVerify implements IVerify {

    @Override
    public boolean security() {

        System.out.println("执行密码支付校验");
        return true;
    }
}
