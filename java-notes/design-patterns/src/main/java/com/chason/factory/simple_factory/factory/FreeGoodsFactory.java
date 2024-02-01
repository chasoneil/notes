package com.chason.factory.simple_factory.factory;

import com.chason.factory.simple_factory.service.IFreeGoods;
import com.chason.factory.simple_factory.service.impl.DiscountFreeGoods;
import com.chason.factory.simple_factory.service.impl.GiftFreeGoods;
import com.chason.factory.simple_factory.service.impl.YoukuMemberFreeGoods;

/**
 * 具体工厂：
 *
 * 作用就是去生成免费商品
 */
public class FreeGoodsFactory {


    public static IFreeGoods getInstance(Integer type) {

        if (type == 1) {
            return new DiscountFreeGoods();
        } else if (type == 2) {
            return new YoukuMemberFreeGoods();
        } else if (type == 3) {
            return new GiftFreeGoods();
        }
        return null;
    }

}
