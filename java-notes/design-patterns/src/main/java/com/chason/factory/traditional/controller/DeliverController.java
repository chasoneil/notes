package com.chason.factory.traditional.controller;

import com.chason.factory.traditional.entity.AwardInfo;
import com.chason.factory.traditional.entity.DiscountResult;
import com.chason.factory.traditional.entity.GiftInfo;
import com.chason.factory.traditional.service.DiscountService;
import com.chason.factory.traditional.service.GiftService;
import com.chason.factory.traditional.service.YoukuMemberService;

import java.util.UUID;

public class DeliverController {

    /**
     * 按照类型的不同发放不同的奖品
     * @param info
     */
    public void awardToUser (AwardInfo info) {

        if (info.getType() == 1) {
            DiscountService service = new DiscountService();
            DiscountResult result = service.sendDiscount(info.getUid(), info.getAwardNumber());
            System.out.println("打折券发送成功! " + result);
        } else if (info.getType() == 2) {

            String phone = info.getExtMap().get("phone");
            YoukuMemberService service = new YoukuMemberService();
            service.registerMember(phone, info.getAwardNumber());
            System.out.println("优酷会员发放成功");

        } else if (info.getType() == 3) {

            GiftInfo giftInfo = new GiftInfo();
            giftInfo.setUsername(info.getExtMap().get("username"));
            giftInfo.setAddress(info.getExtMap().get("address"));
            giftInfo.setUserPhone(info.getExtMap().get("phone"));
            giftInfo.setOrderId(UUID.randomUUID().toString());

            GiftService giftService = new GiftService();
            boolean result = giftService.sendGift(giftInfo);

            if (result) {
                System.out.println("小礼品发放成功");
            }

        }

    }

}
