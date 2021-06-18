package com.huasheng.wmssystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Schema(description = "角色表")
@SQLDelete(sql = "update [Roles] set status = -1 where role_id = ?")
@Where(clause = "status <> -1")
public class Roles implements Serializable {

    @Schema(description = "角色ID")
    @Id
    private String roleId;

    @Schema(description = "角色名称")
    private String roleName;

    @Schema(description = "描述")
    private String description;

    @Schema(description = "0:禁用;1:启用", hidden = true)
    private boolean enable;

    @Schema(hidden = true)
    private int status;

    @Schema(hidden = true)
    private String addUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date addTime;

}

