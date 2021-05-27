package com.huasheng.wmssystem.controller;

import com.huasheng.wmssystem.core.service.UserService;
import com.huasheng.wmssystem.domain.entity.User;
import com.huasheng.wmssystem.domain.model.paramodel.LoginPara;
import com.huasheng.wmssystem.domain.model.resultmodel.ResultBase;
import com.huasheng.wmssystem.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/24 14:02
 * @Description ：账号登录控制
 */
@RestController
@Slf4j
@RequestMapping("/api")
public class AccountController {

    @RequiresAuthentication
    @GetMapping("/hello")
    public String hello() {

        return "welcome to wms!";
    }

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
    @ApiOperation(value = "用户登录")
    public ResultBase login(@Validated @RequestBody LoginPara loginPara, HttpServletResponse response) {

        User user = userService.findByUserName(loginPara.getUsername());
        Assert.notNull(user, "用户不存在");

        if (!user.getPassword().equalsIgnoreCase(SecureUtil.md5(loginPara.getPassword()))) {
            return ResultBase.fail("20005", "密码不正确");
        }
        String jwt = jwtUtils.generateToken(user.getUserId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");

        return ResultBase.succ(MapUtil.builder()
                .put("id", user.getUserId())
                .put("username", user.getUsername())
                .map()
        );
    }

    @GetMapping("/logout")
    public ResultBase logout() {
        SecurityUtils.getSubject().logout();
        return ResultBase.succ(null);
    }


}
