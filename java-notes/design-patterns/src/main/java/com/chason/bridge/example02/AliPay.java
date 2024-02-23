package com.chason.bridge.example02;

import java.math.BigDecimal;

public class AliPay extends AbstractPay {

    public AliPay(IVerify verify) {
        super(verify);
    }

    @Override
    public void pay(BigDecimal cost) {
        System.out.println("使用支付宝进行支付...");
        if (verify.security()) {
            System.out.println("支付成功！支付金额：" + cost);
        } else {
            System.out.println("支付失败");
        }
    }
}
