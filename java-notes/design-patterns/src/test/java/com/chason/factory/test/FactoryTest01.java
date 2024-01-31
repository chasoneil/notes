package com.chason.factory.test;

import com.chason.factory.schema01.controller.DeliverController;
import com.chason.factory.schema01.entity.AwardInfo;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 最初代码的测试
 */
public class FactoryTest01 {

    DeliverController controller = new DeliverController();

    @Test
    public void test01() {

        AwardInfo info = new AwardInfo();
        info.setUid("007");
        info.setAwardNumber("0.7count_0001");
        info.setType(1);
        controller.awardToUser(info);

    }

    @Test
    public void test02() {

        AwardInfo info = new AwardInfo();
        info.setUid("007");
        info.setAwardNumber("Youku_0001");

        Map<String, String> extMap = new HashMap<>();
        extMap.put("phone", "18901239876");
        info.setExtMap(extMap);
        info.setType(2);

        controller.awardToUser(info);
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
        controller.awardToUser(info);

    }

}
