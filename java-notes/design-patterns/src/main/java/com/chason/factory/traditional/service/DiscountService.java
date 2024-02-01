package com.chason.factory.traditional.service;

import com.chason.factory.traditional.entity.DiscountResult;

/**
 *
 */
public class DiscountService {

    public DiscountResult sendDiscount (String uid, String awardNumber) {

        System.out.println("向用户" + uid +  "发送一张打折券：" + awardNumber);
        return new DiscountResult(200, "发放打折券成功！");

    }
}
