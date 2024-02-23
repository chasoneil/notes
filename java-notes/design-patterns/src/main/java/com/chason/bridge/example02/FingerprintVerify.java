package com.chason.bridge.example02;

public class FingerprintVerify implements IVerify {

    @Override
    public boolean security() {
        System.out.println("执行指纹校验");
        return true;
    }
}
