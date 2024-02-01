package com.chason.factory.test;

import com.chason.factory.factory_method.controller.DeliverController;
import com.chason.factory.simple_factory.entity.AwardInfo;
import com.chason.factory.simple_factory.entity.ResponseResult;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 简单工厂的测试  simple_factory
 *
 * 工厂方法测试，测试的逻辑都是一样的
 */
public class FactoryTest02 {

    private DeliverController controller = new DeliverController();

    @Test
    public void test01 () {

        AwardInfo awardInfo = new AwardInfo();
        awardInfo.setUid("007");
        awardInfo.setType(1);
        awardInfo.setAwardNumber("discount_0001");

        ResponseResult result = controller.awardToUser(awardInfo);
        System.out.println(result);

    }


    @Test
    public void test02() {

        AwardInfo info = new AwardInfo();
        info.setUid("007");
        info.setAwardNumber("youku_0001");

        Map<String, String> extMap = new HashMap<>();
        extMap.put("phone", "18901239876");
        info.setExtMap(extMap);
        info.setType(2);
        ResponseResult result = controller.awardToUser(info);
        System.out.println(result);
    }

    @Test
    public void test03() {

        AwardInfo info = new AwardInfo();
        info.setUid("007");
        info.setAwardNumber("gift_001");

        Map<String, String> extMap = new HashMap<>();
        extMap.put("phone", "18901239876");
        extMap.put("username", "张三");
        extMap.put("address", "上海市浦东新区");
        info.setExtMap(extMap);

        info.setType(3);
        ResponseResult result = controller.awardToUser(info);
        System.out.println(result);

    }

}
