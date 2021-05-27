package com.huasheng.wmssystem.domain.model.resultmodel;

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

    private Object data;

    public static ResultBase succ(Object data) {
        ResultBase r = new ResultBase();
        r.setCode("00000");
        r.setMsg("成功");
        r.setData(data);
        return r;
    }

    public static ResultBase succ(String msg, Object data) {
        ResultBase r = new ResultBase();
        r.setCode("00000");
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

    public static ResultBase fail(String code, String msg) {
        ResultBase r = new ResultBase();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }

    public static ResultBase fail(String code, String msg, Object data) {
        ResultBase r = new ResultBase();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }


}
