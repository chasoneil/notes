package com.chason.factory.simple_factory.service;

import com.chason.factory.simple_factory.entity.AwardInfo;
import com.chason.factory.simple_factory.entity.ResponseResult;

/**
 * 免费商品的发放接口
 */
public interface IFreeGoods {

    ResponseResult sendFreeGoods(AwardInfo awardInfo);

}
