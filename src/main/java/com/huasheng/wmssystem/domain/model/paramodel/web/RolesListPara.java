package com.huasheng.wmssystem.domain.model.paramodel.web;

import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class RolesListPara extends ListPara {

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "描述")
    private String description;

}

