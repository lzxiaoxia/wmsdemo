package com.huasheng.wmssystem.controller;

import cn.hutool.crypto.SecureUtil;
import com.huasheng.wmssystem.core.service.UserService;
import com.huasheng.wmssystem.domain.entity.User;
import com.huasheng.wmssystem.domain.model.paramodel.LoginPara;
import com.huasheng.wmssystem.domain.model.resultmodel.DataResult;
import com.huasheng.wmssystem.domain.model.resultmodel.LoginResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ResultBase;
import com.huasheng.wmssystem.exception.CommonErrorEnums;
import com.huasheng.wmssystem.exception.GlobalExceptionHandler;
import com.huasheng.wmssystem.exception.NotFoundException;
import com.huasheng.wmssystem.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/24 14:02
 * @Description ：账号登录控制
 */
@RestController
@Slf4j
@RequestMapping("/account")
public class AccountController extends GlobalExceptionHandler {

    @GetMapping("/hello")
    public String hello() {

        return "welcome to wms!";
    }


    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;


    @PostMapping("/login")
//    @ApiOperation(value = "用户登录")
    public DataResult<LoginResult> login(@Validated @RequestBody LoginPara loginPara, HttpServletResponse response) {

        User user = userService.findByUserName(loginPara.getUsername());

        if (user == null || !user.getPassword().equalsIgnoreCase(SecureUtil.md5(loginPara.getPassword()))) {
            throw new NotFoundException(CommonErrorEnums.WRONG_USERNAME_PWD);
        }

        String jwt = jwtUtils.generateToken(user.getUserId());

        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");


        DataResult<LoginResult> dataResult = new DataResult<>();

        LoginResult loginResult = new LoginResult();
        loginResult.setId(user.getUserId());
        loginResult.setUsername(user.getUsername());

        return dataResult.succ(loginResult)
                ;
    }

    @GetMapping("/logout")
    public ResultBase logout() {
        SecurityUtils.getSubject().logout();
        return ResultBase.succ();
    }


}
