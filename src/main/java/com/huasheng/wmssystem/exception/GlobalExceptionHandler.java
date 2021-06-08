package com.huasheng.wmssystem.exception;

import com.huasheng.wmssystem.domain.model.resultmodel.ResultBase;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/17 16:28
 * @Description ：全局异常处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({ UnauthenticatedException.class, AuthenticationException.class })
    public ResultBase authenticationException() {
        return ResultBase.fail("20002", "token已失效，请重新登录");

    }

    @ExceptionHandler({ UnauthorizedException.class, AuthorizationException.class })
    public ResultBase authorizationException() {
        return ResultBase.fail("20002", "token已失效，请重新登录");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public ResultBase handler(ShiroException e) {
        log.error("运行时异常：----------------{}", e);
        return ResultBase.fail("20001", "运行时异常：" + e.getMessage(), null);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultBase handler(MethodArgumentNotValidException e) {
        log.error("实体校验异常：----------------{}", e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();

        return ResultBase.fail("20002", "实体校验异常：" + objectError.getDefaultMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ExpiredCredentialsException.class)
    public ResultBase handler(ExpiredCredentialsException e) {
//        log.error("实体校验异常：----------------{}", e);

        return ResultBase.fail("20002", "token已失效，请重新登录");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NotFoundException.class)
    public ResultBase handler(CommonErrorEnums e) {
        log.error("找不到实体：----------------{}", e);
        return ResultBase.fail("10002", "找不到对应实体");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultBase handler(IllegalArgumentException e) {
        log.error("Assert异常：----------------{}", e);
        return ResultBase.fail("10003", "类型转换异常：" + e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ResultBase handler(RuntimeException e) {
        log.error("运行时异常：----------------{}", e);
        return ResultBase.fail("20001", "运行时异常：" + e.getMessage());
    }

    //全局通用异常
  /*  @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public ResultBase handler(Exception e) {
        log.error("系统错误：----------------{}", e);

        return ResultBase.fail("10030", "[系统错误！" + e.getClass().getName() + ": " + e.getLocalizedMessage() + "]");
    }*/



}
