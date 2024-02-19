package com.chason.factory.abstract_factory.controller;

import com.chason.factory.abstract_factory.factory.ApplienceFactory;
import com.chason.factory.abstract_factory.product.IFridge;
import com.chason.factory.abstract_factory.product.ITV;

public class ApplienceController {

    private ITV tv;

    private IFridge fridge;


    public ApplienceController (ApplienceFactory factory) {
        this.tv = factory.createTV();
        this.fridge = factory.createFridge();
    }

    public ITV getTv() {
        return tv;
    }

    public IFridge getFridge() {
        return fridge;
    }
}
