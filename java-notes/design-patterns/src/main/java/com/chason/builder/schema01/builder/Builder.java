package com.chason.builder.schema01.builder;

import com.chason.builder.schema01.product.Bike;

/**
 * abstract builder
 *
 * builder contains create product method and build frame method
 *
 */
public abstract class Builder {

    protected Bike mBike = new Bike();

    public abstract void buildFrame();

    public abstract void buildSeat();

    public abstract Bike createBike();

}
