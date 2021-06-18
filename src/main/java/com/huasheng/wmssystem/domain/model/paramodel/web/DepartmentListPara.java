package com.huasheng.wmssystem.domain.model.paramodel.web;

import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class DepartmentListPara extends ListPara {

    @Schema(description = "部门名")
    private String departmentName;

    @Schema(description = "备注")
    private String description;

}

