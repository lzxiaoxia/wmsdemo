package com.huasheng.wmssystem.domain.model.resultmodel;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/13 16:04
 * @Description ：返回列表结果
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ListResult<T> extends ResultBase {

    private T data;

    private long total;

    private int pageTotal;

    public ListResult<List<T>> succ(List<T> data, long total, int pageTotal) {
        ListResult<List<T>> r = new ListResult<>();
        r.setPageTotal(pageTotal);
        r.setTotal(total);
        r.setCode("00000");
        r.setMsg("成功");
        r.setData(data);
        return r;
    }

    public ListResult<T> succ(long total, int pageTotal) {
        ListResult<T> r = new ListResult<>();
        r.setPageTotal(pageTotal);
        r.setTotal(total);
        r.setCode("00000");
        r.setMsg("成功");
        r.setData(null);
        return r;
    }

    public ListResult<List<T>> succ(Page<T> page) {
        ListResult<List<T>> r = new ListResult<>();
        r.setPageTotal(page.getTotalPages());
        r.setTotal(page.getTotalElements());
        r.setCode("00000");
        r.setMsg("成功");
        r.setData(page.getContent());
        return r;
    }

    public ListResult<List<T>> succ(Page<T> page, String msg) {
        ListResult<List<T>> r = new ListResult<>();
        r.setPageTotal(page.getTotalPages());
        r.setTotal(page.getTotalElements());
        r.setCode("00000");
        r.setMsg(msg);
        r.setData(page.getContent());
        return r;
    }

}
