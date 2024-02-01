package com.chason.factory.traditional.service;

import com.chason.factory.traditional.entity.GiftInfo;

public class GiftService {

    public boolean sendGift (GiftInfo info) {
        System.out.println("小礼品已发送，获奖用户注意查收");
        System.out.println(info.toString());
        return true;
    }

}
