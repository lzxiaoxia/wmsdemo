package com.huasheng.wmssystem.exception;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/6/10 10:03
 * @Description ：自定义找不到实体异常
 */
public class NotFoundException extends RuntimeException {

    private final CommonErrorEnums errorEnums;

    public NotFoundException(CommonErrorEnums commonErrorEnums) {
        super(commonErrorEnums.getCodeStr());
        this.errorEnums=commonErrorEnums;
    }

    public CommonErrorEnums getErrorEnums() {
        return errorEnums;
    }
}