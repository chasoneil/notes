package com.chason.factory.simple_factory.service.impl;

import com.chason.factory.simple_factory.entity.AwardInfo;
import com.chason.factory.simple_factory.entity.ResponseResult;
import com.chason.factory.simple_factory.service.IFreeGoods;

import java.util.Map;

public class YoukuMemberFreeGoods implements IFreeGoods {


    @Override
    public ResponseResult sendFreeGoods(AwardInfo awardInfo) {

        Map<String, String> extMap = awardInfo.getExtMap();

        System.out.println("发放优酷会员成功：" + extMap.get("phone") + ", " + awardInfo.getAwardNumber());
        return new ResponseResult(200, "发放成功");
    }
}
