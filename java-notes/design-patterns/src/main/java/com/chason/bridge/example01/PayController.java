package com.chason.bridge.example01;

import java.math.BigDecimal;

/**
 * 模拟不同支付方式和验证方式的支付
 */
public class PayController {


    /**
     *
     * @param cost     本次支付金额
     * @param payType  支付方式  1微信  2支付宝
     * @param verifyType  验证方式  1密码  2人脸  3指纹
     *
     * 使用这种方式  如果将来我们增加支付方式或者增加验证方式，会比较麻烦
     * 修改的代码很多
     *
     */
    public void pay(BigDecimal cost, int payType, int verifyType) {

        if (payType == 1) {
            System.out.println("正在使用微信进行结算...");
            if (verifyType == 1) {
                System.out.println("密码支付成功, 支付金额：" + cost);
            } else if (verifyType == 2) {
                System.out.println("人脸支付成功, 支付金额：" + cost);
            } else if (verifyType == 3) {
                System.out.println("指纹支付成功, 支付金额：" + cost);
            }
        }

        if (payType == 2) {
            System.out.println("正在使用支付宝进行结算...");
            if (verifyType == 1) {
                System.out.println("密码支付成功, 支付金额：" + cost);
            } else if (verifyType == 2) {
                System.out.println("人脸支付成功, 支付金额：" + cost);
            } else if (verifyType == 3) {
                System.out.println("指纹支付成功, 支付金额：" + cost);
            }
        }

    }


}
