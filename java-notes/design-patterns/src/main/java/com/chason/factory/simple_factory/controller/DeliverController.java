package com.chason.factory.simple_factory.controller;

import com.chason.factory.simple_factory.entity.AwardInfo;
import com.chason.factory.simple_factory.entity.ResponseResult;
import com.chason.factory.simple_factory.factory.FreeGoodsFactory;
import com.chason.factory.simple_factory.service.IFreeGoods;

public class DeliverController {


    public ResponseResult awardToUser (AwardInfo info) {

        IFreeGoods iFreeGoods = FreeGoodsFactory.getInstance(info.getType());
        return iFreeGoods.sendFreeGoods(info);
    }

}
