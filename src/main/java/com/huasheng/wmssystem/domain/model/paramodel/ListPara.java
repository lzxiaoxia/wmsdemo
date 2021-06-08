package com.huasheng.wmssystem.domain.model.paramodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/13 15:45
 * @Description ：请求参数基类
 */

@Data
@ApiModel(value = "列表请求基类")
public class ListPara {


    @ApiModelProperty(value = "页数", example = "1")
    @Parameter(example = "1")
    private int page = 1;

    @ApiModelProperty(value = "每页数量", example = "10")
    @Parameter(example = "10")
    private int pageSize = 10;

}




