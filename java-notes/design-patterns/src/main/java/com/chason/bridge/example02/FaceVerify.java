package com.chason.bridge.example02;

public class FaceVerify implements IVerify {

    @Override
    public boolean security() {
        System.out.println("执行人脸识别校验");
        return true;
    }
}
