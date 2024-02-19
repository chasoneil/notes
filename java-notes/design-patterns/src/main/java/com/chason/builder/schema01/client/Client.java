package com.chason.builder.schema01.client;

import com.chason.builder.schema01.builder.MoBikeBuilder;
import com.chason.builder.schema01.director.BikeDirector;
import com.chason.builder.schema01.product.Bike;

/**
 * on this pattern
 * Client communicate with director
 */
public class Client {

    public static void main(String[] args) {

        BikeDirector director = new BikeDirector(new MoBikeBuilder());
        Bike bike = director.build();

        System.out.println(bike);


    }

}
