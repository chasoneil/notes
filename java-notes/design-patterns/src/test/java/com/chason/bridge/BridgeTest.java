package com.chason.bridge;

import com.chason.bridge.example01.PayController;
import com.chason.bridge.example02.AbstractPay;
import com.chason.bridge.example02.AliPay;
import com.chason.bridge.example02.PasswordVerify;
import org.junit.Test;

import java.math.BigDecimal;

public class BridgeTest {

    /**
     * 使用最传统的方式进行测试
     */
    @Test
    public void test01 () {
        PayController payController = new PayController();
        payController.pay(new BigDecimal(100), 1, 2);

    }


    @Test
    public void test02 () {

        AbstractPay pay = new AliPay(new PasswordVerify());
        pay.pay(new BigDecimal(100));

    }


}
