package com.chason.factory.abstract_factory.factory;

import com.chason.factory.abstract_factory.product.IFridge;
import com.chason.factory.abstract_factory.product.ITV;
import com.chason.factory.abstract_factory.product.impl.HaierFridge;
import com.chason.factory.abstract_factory.product.impl.HaierTV;

public class HaierFactory implements ApplienceFactory {

    @Override
    public ITV createTV() {
        return new HaierTV();
    }

    @Override
    public IFridge createFridge() {
        return new HaierFridge();
    }
}
