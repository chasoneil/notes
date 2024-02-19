package com.chason.factory.abstract_factory.factory;

import com.chason.factory.abstract_factory.product.IFridge;
import com.chason.factory.abstract_factory.product.ITV;
import com.chason.factory.abstract_factory.product.impl.TclFridge;
import com.chason.factory.abstract_factory.product.impl.TclTV;

public class TclFactory implements ApplienceFactory {

    @Override
    public ITV createTV() {
        return new TclTV();
    }

    @Override
    public IFridge createFridge() {
        return new TclFridge();
    }
}
