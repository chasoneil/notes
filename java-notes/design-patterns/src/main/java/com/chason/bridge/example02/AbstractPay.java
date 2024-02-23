package com.chason.bridge.example02;

import java.math.BigDecimal;

/**
 * 抽象的支付
 */
public abstract class AbstractPay {

    protected IVerify verify;

    public AbstractPay(IVerify verify) {
        this.verify = verify;
    }

    public abstract void pay(BigDecimal cost);

}
