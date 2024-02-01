package com.chason.factory.simple_factory.service.impl;

import com.chason.factory.simple_factory.entity.AwardInfo;
import com.chason.factory.simple_factory.entity.GiftInfo;
import com.chason.factory.simple_factory.entity.ResponseResult;
import com.chason.factory.simple_factory.service.IFreeGoods;

import java.util.UUID;

public class GiftFreeGoods implements IFreeGoods {


    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {

        GiftInfo giftInfo = new GiftInfo();
        giftInfo.setUsername(awardInfo.getExtMap().get("username"));
        giftInfo.setAddress(awardInfo.getExtMap().get("address"));
        giftInfo.setUserPhone(awardInfo.getExtMap().get("phone"));
        giftInfo.setOrderId(UUID.randomUUID().toString());

        System.out.println("发放小礼品 ：" + giftInfo);

        return new ResponseResult(200, "发放成功");
    }
}
