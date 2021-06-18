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
@Schema(description = "部门管理表")
@SQLDelete(sql = "update [Department] set status = -1 where department_id = ?")
@Where(clause = "status <> -1")
public class Department implements Serializable {
   
    @Schema(description = "部门ID")
    @Id
	private String departmentId;

    @Schema(description = "上级Id")
	private String parentId;

    @Schema(description = "部门名")
	private String departmentName;

    @Schema(description = "备注")
	private String description;

    @Schema(hidden = true)
	private int status;

    @Schema(hidden = true)
	private String addUser;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(hidden = true)
	private Date addTime;

}

