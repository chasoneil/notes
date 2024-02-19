package com.chason.builder.schema01.builder;

import com.chason.builder.schema01.product.Bike;

public class MoBikeBuilder extends Builder {


    @Override
    public void buildFrame() {
        mBike.setFrame("铝合金车架");
    }

    @Override
    public void buildSeat() {
        mBike.setSeat("真皮坐垫");
    }

    @Override
    public Bike createBike() {
        return mBike;
    }
}
