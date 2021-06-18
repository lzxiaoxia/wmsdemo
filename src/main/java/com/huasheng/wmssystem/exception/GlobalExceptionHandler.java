package com.huasheng.wmssystem.exception;

import com.huasheng.wmssystem.domain.model.resultmodel.ResultBase;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.validation.ConstraintViolationException;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/17 16:28
 * @Description ：全局异常处理
 */

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthenticatedException.class, AuthenticationException.class})
    public ResultBase authenticationException() {
        return ResultBase.fail("20002", "token已失效，请重新登录");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnauthorizedException.class, AuthorizationException.class})
    public ResultBase authorizationException() {
        return ResultBase.fail("200022", "token已失效，请重新登录");
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public ResultBase handler(ShiroException e) {
        log.error("运行时异常：----------------{}", e);
        return ResultBase.fail(CommonErrorEnums.USER_VERIFY_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
//    @ApiResponse(responseCode = "20002",description = "用户登录过期")
    public ResultBase handler(MethodArgumentNotValidException e) {
        log.error("参数校验异常：----------------{}", e);
        BindingResult bindingResult = e.getBindingResult();
        StringBuilder sb = new StringBuilder("");
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            sb.append(fieldError.getField()).append("：").append(fieldError.getDefaultMessage()).append(", ");
        }

        return ResultBase.fail(CommonErrorEnums.PARAMETER_ERROR, sb.toString());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ConstraintViolationException.class)
//    @ApiResponse(responseCode = "20002",description = "用户登录过期")
    public ResultBase handler(ConstraintViolationException e) {
        log.error("参数校验异常：----------------{}", e);

        return ResultBase.fail(CommonErrorEnums.PARAMETER_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = NotFoundException.class)
    public ResultBase handler(NotFoundException e) {
        log.error("找不到实体：----------------{}", e);

        return ResultBase.fail(e.getErrorEnums());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = CustomException.class)
    public ResultBase handler(CustomException e) {
        log.error(e.getErrorEnums().getMsg() + "_自定义异常：----------------{}", e);

        return ResultBase.fail(e.getErrorEnums());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "类型转换异常")
//    @ResponseStatus(CommonErrorEnums.TYPE_CONVERSION_EXCEPTION.)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResultBase handler(IllegalArgumentException e) {
        log.error("Assert类型转换异常：----------------{}", e);
        return ResultBase.fail(CommonErrorEnums.TYPE_CONVERSION_EXCEPTION, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = InvalidDataAccessResourceUsageException.class)
    public ResultBase handler(InvalidDataAccessResourceUsageException e) {
        log.error("数据访问异常：----------------{}", e);
        return ResultBase.fail(CommonErrorEnums.DATA_ACCESS_ERROR, e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public ResultBase handler(RuntimeException e) {
        log.error("运行时异常：----------------{}", e);
        return ResultBase.fail(CommonErrorEnums.UNKNOWN_SYSTEM_EXCEPTION, e.getMessage());
    }

    //全局通用异常
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = Exception.class)
    public ResultBase handler(Exception e) {
        log.error("系统错误：----------------{}", e);
        return ResultBase.fail(CommonErrorEnums.UNKNOWN_SYSTEM_EXCEPTION, "[系统错误！" + e.getClass().getName() + ": " + e.getLocalizedMessage() + "]");
    }


}
