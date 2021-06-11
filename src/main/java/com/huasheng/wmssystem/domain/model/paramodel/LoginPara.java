package com.huasheng.wmssystem.domain.model.paramodel;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/24 14:11
 * @Description ：
 */
@Data
//@ApiModel(value = "登录参数")
public class LoginPara implements Serializable {

    @NotBlank(message = "昵称不能为空")
//    @ApiModelProperty(value = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
//    @ApiModelProperty(value = "密码")
    private String password;

}
