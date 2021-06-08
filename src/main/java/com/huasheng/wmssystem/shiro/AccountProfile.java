package com.huasheng.wmssystem.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
/**
 * @Description 缓存账户信息
 * @Author      xjTang
 * @Date        Create by 2021/5/18 10:00
 */
public class AccountProfile implements Serializable {

    private Long id;

    private String username;

}
