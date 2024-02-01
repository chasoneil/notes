package com.chason.factory.traditional.entity;

import lombok.Data;

import java.util.Map;

/**
 *
 * award info 奖品信息
 *
 * 奖品一共有三类
 * 1. 礼品券 打折券
 * 2. 优酷会员
 * 3. 小礼品
 *
 */
@Data
public class AwardInfo {

    private String uid;

    /*
       1. 礼品券
       2. 优酷会员
       3. 小礼品
     */
    private Integer type;

    private String awardNumber;

    private Map<String, String> extMap;

}
