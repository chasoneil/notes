package com.chason.factory.abstract_factory.factory;

import com.chason.factory.abstract_factory.product.IFridge;
import com.chason.factory.abstract_factory.product.ITV;

public interface ApplienceFactory {

    ITV createTV();

    IFridge createFridge();

}
