package com.huasheng.wmssystem.domain.model.resultmodel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/6/9 14:44
 * @Description ：
 */
@Data
public class LoginResult {

    @ApiModelProperty(value = "用户id")
    private String id;

    @ApiModelProperty(value = "用户名")
    private String username;
}
