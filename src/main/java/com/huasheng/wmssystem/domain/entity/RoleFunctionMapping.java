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
@Schema(description = "角色功能映射表")
@SQLDelete(sql = "update [RoleFunctionMapping] set status = -1 where role_function_id = ?")
@Where(clause = "status <> -1")
public class RoleFunctionMapping implements Serializable {
   
    @Schema(description = "角色功能编号")
    @Id
	private String roleFunctionId;

    @Schema(description = "角色编号")
	private String roleId;

    @Schema(description = "功能编号")
	private String functionId;

    @Schema(hidden = true)
	private String addUser;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(hidden = true)
	private Date addTime;
	
}

