package com.huasheng.wmssystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "[User]")
@Schema(description = "用户表")
@SQLDelete(sql = "update [User] set status = -1 where user_id = ?")
@Where(clause = "status <> -1")
public class User implements Serializable {

    @Schema(description = "用户ID")
    @Id
    private String userId;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "工号")
    private String userNumber;

    @Schema(description = "姓名")
    private String realName;

    @Schema(description = "固话")
    private String fixedPhone;

    @Schema(description = "手机")
    private String phoneNumber;

    @Schema(description = "邮箱")
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    @Schema(description = "密码", hidden = true)
    private String password;

    @Schema(description = "部门ID")
    private String departmentId;

    @Schema(description = "备注")
    private String description;

    @Schema(hidden = true)
    private int status;

    @Schema(description = "是否锁定", hidden = true)
    private boolean lockFlag;

    @Schema(description = "是否忘记密码", hidden = true)
    private boolean forgetFlag;

    @Schema(hidden = true)
    private String addUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(hidden = true)
    private Date addTime;

    @Schema(hidden = true)
    private String editUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(hidden = true)
    private Date editTime;

//    @OneToOne
//    @JoinColumn(name = "departmentId")
//    private Department department;

}

