package com.huasheng.wmssystem.exception;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/6/10 14:39
 * @Description ：自定义异常
 */
public class CustomException extends RuntimeException {

    private final CommonErrorEnums errorEnums;

    public CustomException(CommonErrorEnums commonErrorEnums) {
        super(commonErrorEnums.getCodeStr());
        this.errorEnums=commonErrorEnums;
    }

    public CommonErrorEnums getErrorEnums() {
        return errorEnums;
    }
}