package com.chason.factory.test;

import com.chason.factory.abstract_factory.controller.ApplienceController;
import com.chason.factory.abstract_factory.factory.HaierFactory;
import com.chason.factory.abstract_factory.product.IFridge;
import com.chason.factory.abstract_factory.product.ITV;
import org.junit.Test;

/**
 * 测试抽象工厂的测试类
 *
 */
public class FactoryTest03 {

    @Test
    public void test01 () {
        ApplienceController controller = new ApplienceController(new HaierFactory());

        ITV tv = controller.getTv();
        IFridge fridge = controller.getFridge();

        System.out.println(tv);
        System.out.println(fridge);
    }


}
