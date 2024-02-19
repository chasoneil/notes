package com.chason.builder.schema01.director;

import com.chason.builder.schema01.builder.Builder;
import com.chason.builder.schema01.product.Bike;

public class BikeDirector {

    private Builder builder;

    public BikeDirector (Builder builder) {
        this.builder = builder;
    }

    public Bike build () {

        builder.buildFrame();
        builder.buildSeat();
        return builder.createBike();
    }


}
