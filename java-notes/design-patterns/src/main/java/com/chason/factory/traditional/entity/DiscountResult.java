package com.chason.factory.traditional.entity;

import lombok.Data;

/**
 * 打折券发放情况
 *
 */
@Data
public class DiscountResult {

    private Integer status;

    private String message;

    public DiscountResult (Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}
