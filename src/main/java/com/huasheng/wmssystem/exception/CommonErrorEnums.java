package com.huasheng.wmssystem.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommonErrorEnums {

    RESULT_SUCCESS(0, "Result success", "请求成功"),

    /*通用错误以100开头，可直接展示*/
    PARSING_ERROR(10000, "Data parse error", "数据解析错误"),
    PARAMETER_ERROR(10001, "Parameter error", "参数错误"),
    ENTITY_NOT_FOUND_ERROR(10002, "Entity not found", "找不到对应实体"),
    TYPE_CONVERSION_EXCEPTION(10003, "Type conversion error", "类型转换异常"),
//    COMMUNICATION_ERROR(10003, "Communication error", "与其他模块通讯错误"),
    WRONG_USERNAME_PWD(10004, "Wrong username or password", " 用户名或密码错误"),
//    USER_RESIGNED(10005, "User has resigned", "用户已经离职"),
//    ROLE_NOT_FOUND(10006, "RoLe not found", " 角色没有找到"),
    USER_NOT_F0UND(10007, "User not found", "找不到对应用户"),
//    RESULT_NOT_F0UND(10008, "Result not found", "找不到对应实体"),
    FILE_NOT_F0UND(10009, "File not found", "找不到对应文件"),

    /*系统错误以200开头，需做相应处理*/
    UNKNOWN_SYSTEM_EXCEPTION(20001, "Unknown system exception", "系统未知异常"),
    USER_LOGIN_EXPIRED(20002, "User login expired", "用户登录过期"),
    USER_NOT_PERMISSION(20003, "User not permission", "用户没有权限"),
    USER_VERIFY_ERROR(20004, "User verify error", " 用户验证错误")
    ;

    private int code;

    private String messageEn;

    private String messageCn;

    public String getCodeStr()
    {
        if(code==0)
            return "00000";

        return Integer.toString(code);
    }

    public String getMsg(){
        return messageCn;
    }

}
