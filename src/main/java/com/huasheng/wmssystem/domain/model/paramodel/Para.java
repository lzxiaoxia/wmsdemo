package com.huasheng.wmssystem.domain.model.paramodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/13 15:45
 * @Description ：请求参数基类
 */

@Getter
@Setter
//@ApiModel(value = "登录jwt")
public class Para {

    @ApiModelProperty(value = "用户id")
    private String uid;

    @ApiModelProperty(value = "用户token")
    private String token;

}




