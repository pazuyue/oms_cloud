package com.oms.saas.commodity.api;


import lombok.Data;

/**
 * APi返回的状态码表
 * @author cc
 * @date 2021-07-12 8:58
 */

public enum ResultCode {
    /**
     * api状态码管理
     * @author cc
     * @date 2021-07-12 10:00
     */
    SUCCESS(10000, "请求成功"),
    FAILED(10001, "操作失败"),
    TOKEN_FAILED(10002, "token失效"),
    PARAM_ERROR(10003,"参数错误"),
    NONE(99999, "无"),
    CURRENTLIMITING(10005, "服务降级返回");

    private int code;
    private String msg;

    private ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
