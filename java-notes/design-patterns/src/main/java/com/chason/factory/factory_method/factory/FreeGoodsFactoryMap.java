package com.chason.factory.factory_method.factory;

import com.chason.factory.factory_method.factory.impl.DiscountGoodsFactory;
import com.chason.factory.factory_method.factory.impl.GiftGoodsFactory;
import com.chason.factory.factory_method.factory.impl.YoukuMemberGoodFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 工厂类的工厂
 * 用来创建工厂类对象
 */
public class FreeGoodsFactoryMap {


    private static final Map<Integer, IFreeGoodsFactory> cachedFactoies = new HashMap<>();

    static {

        cachedFactoies.put(1, new DiscountGoodsFactory());
        cachedFactoies.put(2, new YoukuMemberGoodFactory());
        cachedFactoies.put(3, new GiftGoodsFactory());
    }

    public static IFreeGoodsFactory getGoodsFactory (Integer type) {
        return cachedFactoies.get(type);
    }

    private FreeGoodsFactoryMap () {}

}
