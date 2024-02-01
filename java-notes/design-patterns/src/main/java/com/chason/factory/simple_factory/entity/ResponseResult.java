package com.chason.factory.simple_factory.entity;

import lombok.Data;

/**
 * 打折券发放情况
 *
 */
@Data
public class ResponseResult {

    private Integer status;

    private String message;

    private Object data;

    public ResponseResult(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

}
