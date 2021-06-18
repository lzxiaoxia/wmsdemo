package com.huasheng.wmssystem.domain.model.paramodel;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Pattern;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/13 15:45
 * @Description ：请求参数基类
 */

@Data
public class ListPara {

    @Schema(description = "页码", example = "1")
    private int page = 1;

    @Schema(description = "每页数量", example = "10")
    private int pageSize = 10;

}




