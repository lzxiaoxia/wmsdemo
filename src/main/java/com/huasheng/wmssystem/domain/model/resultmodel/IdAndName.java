package com.huasheng.wmssystem.domain.model.resultmodel;

import lombok.Data;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/6/18 11:50
 * @Description ：
 */
@Data
public class IdAndName {

    private String id;

    private String name;


    public IdAndName(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
