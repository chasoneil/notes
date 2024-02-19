package com.chason.builder.schema01.builder;

import com.chason.builder.schema01.product.Bike;

public class HelloBuilder extends Builder {

    @Override
    public void buildFrame() {
        mBike.setFrame("不锈钢车架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("复合材料坐垫");
    }

    @Override
    public Bike createBike() {
        return mBike;
    }
}
