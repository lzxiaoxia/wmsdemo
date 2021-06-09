package com.huasheng.wmssystem.domain.model.resultmodel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
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

    public ListResult<List<T>> succ(Page<T> page) {
        ListResult<List<T>> r = new ListResult<>();
        r.setPageTotal(page.getTotalPages());
        r.setTotal(page.getTotalPages());
        r.setCode("00000");
        r.setMsg("成功");
        r.setData(page.getContent());
        return r;
    }

    public ListResult<List<T>> succ(Page<T> page, String msg) {
        ListResult<List<T>> r = new ListResult<>();
        r.setPageTotal(page.getTotalPages());
        r.setTotal(page.getTotalPages());
        r.setCode("00000");
        r.setMsg(msg);
        r.setData(page.getContent());
        return r;
    }

}
