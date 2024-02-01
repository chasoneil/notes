package com.chason.factory.factory_method.factory.impl;

import com.chason.factory.factory_method.factory.IFreeGoodsFactory;
import com.chason.factory.simple_factory.service.IFreeGoods;
import com.chason.factory.simple_factory.service.impl.DiscountFreeGoods;

/**
 * 具体工厂
 */
public class DiscountGoodsFactory implements IFreeGoodsFactory {

    @Override
    public IFreeGoods getInstance() {
        return new DiscountFreeGoods();
    }
}
