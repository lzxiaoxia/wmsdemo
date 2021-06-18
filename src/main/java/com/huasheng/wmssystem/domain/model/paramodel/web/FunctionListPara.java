package com.huasheng.wmssystem.domain.model.paramodel.web;

import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class FunctionListPara extends ListPara {

    @Schema(description = "编号")
    private String functionCode;

    @Schema(description = "功能名称")
    private String functionName;

}

