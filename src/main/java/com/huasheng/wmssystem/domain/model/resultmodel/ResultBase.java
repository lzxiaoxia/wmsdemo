package com.huasheng.wmssystem.domain.model.resultmodel;

import com.huasheng.wmssystem.exception.CommonErrorEnums;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/13 15:54
 * @Description ：返回结果基类
 */
@Data
public class ResultBase implements Serializable {

    private String code;

    private String msg;

    public static ResultBase succ() {
        ResultBase r = new ResultBase();
        r.setCode("00000");
        r.setMsg("成功");
        return r;
    }

    public static ResultBase fail(String code, String msg) {
        ResultBase r = new ResultBase();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static ResultBase fail(CommonErrorEnums errorEnums) {
        ResultBase r = new ResultBase();
        r.setCode(errorEnums.getCodeStr());
        r.setMsg(errorEnums.getMsg());
        return r;
    }


    /*正式运行时不返回具体错误信息*/
    public static ResultBase fail(CommonErrorEnums errorEnums, String msg) {
        ResultBase r = new ResultBase();
        r.setCode(errorEnums.getCodeStr());
        r.setMsg(errorEnums.getMsg() + ":" + msg);
        return r;
    }

    public static ResultBase fail(String code, String msg, Object data) {
        ResultBase r = new ResultBase();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }


}
