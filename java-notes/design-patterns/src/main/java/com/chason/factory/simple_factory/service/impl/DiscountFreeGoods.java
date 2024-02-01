package com.chason.factory.simple_factory.service.impl;

import com.chason.factory.simple_factory.entity.AwardInfo;
import com.chason.factory.simple_factory.entity.ResponseResult;
import com.chason.factory.simple_factory.service.IFreeGoods;

public class DiscountFreeGoods implements IFreeGoods {

    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {

        System.out.println("向用户" + awardInfo.getUid() +  "发送一张打折券：" + awardInfo.getAwardNumber());
        return new ResponseResult(200, "发送成功");
    }
}
