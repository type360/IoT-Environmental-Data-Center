package com.briup.smartlabs.common.utils;

import lombok.Getter;

//统一结果响应状态码
@Getter
public enum ResponseCodeEnum {
    /*默认成功，默认失败*/
    SUCCESS(200,"OK"),
    ERROR(500,"系统内部错误，请稍后重试"),

    /* 数据校验失败 */
    PARAM_ERROR(1001, "参数有误"),

    /* 用户错误：2001-2999*/
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_LOGIN_ERROR(4002, "用户名或密码错误"),
    USER_ACCOUNT_FORBIDDEN(4005, "账号已被禁用"),
    USER_ACCESS_DENY(4003,"无访问权限！"),

    /* 资源错误：4001-4999 */
    RESOURCE_NOT_FOUND(4004, "访问的资源不存在"),
    
    DATA_WRONG(5002, "数据错误"),
    DATA_EXISTED(5003, "数据已存在"),
	DATA_NOT_FOUND(5004, "数据不存在");


    private Integer status;
    private String msg;

    ResponseCodeEnum(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

}
