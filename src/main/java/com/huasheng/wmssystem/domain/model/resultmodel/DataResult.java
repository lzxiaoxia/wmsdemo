package com.huasheng.wmssystem.domain.model.resultmodel;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/6/9 14:10
 * @Description ：
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DataResult<T> extends ResultBase {

    private T data;

    public DataResult<T> succ(T data) {
        DataResult<T> r = new DataResult<>();
        r.setCode("00000");
        r.setMsg("成功");
        r.setData(data);
        return r;
    }

    public DataResult<T> succ(String msg, T data) {
        DataResult<T> r = new DataResult<>();
        r.setCode("00000");
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

}
