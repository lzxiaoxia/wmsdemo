package com.huasheng.wmssystem.domain.model.resultmodel.web;

import com.huasheng.wmssystem.domain.model.resultmodel.IdAndName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/6/16 9:37
 * @Description ：
 */
@Data
public class UserInfoResult {

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "工号")
    private String userNumber;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "手机")
    private String phoneNumber;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "固话")
    private String fixedPhone;

    @Schema(description = "部门ID")
    private String departmentId;

    @Schema(description = "部门")
    private String departmentName;

    @Schema(description = "用户角色列表")
    public List<IdAndName> role;


}

