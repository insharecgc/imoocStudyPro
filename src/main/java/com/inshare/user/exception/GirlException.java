package com.inshare.user.exception;

import com.inshare.user.enums.ResultEnum;

public class GirlException extends RuntimeException {
    //RuntimeException会进行事务回滚

    private Integer code;

    public GirlException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
