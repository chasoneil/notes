package com.chason.factory.factory_method.controller;

import com.chason.factory.factory_method.factory.FreeGoodsFactoryMap;
import com.chason.factory.factory_method.factory.IFreeGoodsFactory;
import com.chason.factory.simple_factory.entity.AwardInfo;
import com.chason.factory.simple_factory.entity.ResponseResult;
import com.chason.factory.simple_factory.service.IFreeGoods;

public class DeliverController {

    public ResponseResult awardToUser (AwardInfo info) {

        IFreeGoodsFactory goodsFactory = FreeGoodsFactoryMap.getGoodsFactory(info.getType());
        IFreeGoods iFreeGoods = goodsFactory.getInstance();
        return iFreeGoods.sendFreeGoods(info);
    }

}
