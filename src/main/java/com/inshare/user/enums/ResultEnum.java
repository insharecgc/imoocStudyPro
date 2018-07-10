package com.inshare.user.enums;

public enum ResultEnum {
    UNKNOW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    AGE_OK(1, "你好，你被录用了"),
    QUERRY_NO(100, "查询失败"),
    LITTER(101, "抱歉，你还未成年"),
    BIGGER(102, "抱歉，您已达到退休年龄"),
    ;

    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
