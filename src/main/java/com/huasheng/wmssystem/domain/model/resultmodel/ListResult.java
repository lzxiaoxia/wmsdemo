package com.huasheng.wmssystem.domain.model.resultmodel;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/13 16:04
 * @Description ：返回列表结果
 */
@Getter
@Setter
public class ListResult extends ResultBase{

    private int total;

    private int pageTotal;

    public static ListResult succ(int pageTotal,int total,Object data) {
        ListResult r = new ListResult();
        r.setPageTotal(pageTotal);
        r.setTotal(total);
        r.setCode("00000");
        r.setMsg("成功");
        r.setData(data);
        return r;
    }

    public static ListResult succ(int pageTotal,int total,String msg, Object data) {
        ListResult r = new ListResult();
        r.setPageTotal(pageTotal);
        r.setTotal(total);
        r.setCode("00000");
        r.setMsg(msg);
        r.setData(data);
        return r;
    }

   /* public static ListResult faile(String code, String msg) {
        ListResult r = new ListResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }

    public static ListResult faile(String code, String msg,Object data) {
        ListResult r = new ListResult();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(data);
        return r;
    }*/


}
