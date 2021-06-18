package com.huasheng.wmssystem.domain.model.paramodel.web;

import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/6/16 10:26
 * @Description ：
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserListPara extends ListPara {

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "工号")
    private String userNumber;

    @Schema(description = "角色ID")
    private String roleId;

    @Schema(description = "部门ID")
    private String departmentId;

}
