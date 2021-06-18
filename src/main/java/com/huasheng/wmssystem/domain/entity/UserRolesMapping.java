package com.huasheng.wmssystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Schema(description = "用户角色表")
public class UserRolesMapping implements Serializable {
   
    @Schema(description = "用户角色ID")
    @Id
	private String userRoleId;

    @Schema(description = "角色ID")
	private String roleId;

    @Schema(description = "角色ID")
	private String userId;
	
}

