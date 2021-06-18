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
@Schema(description = "功能设置表")
@SQLDelete(sql = "update [Function] set status = -1 where function_id = ?")
@Where(clause = "status <> -1")
public class Function implements Serializable {
   
    @Schema(description = "功能Id")
    @Id
	private String functionId;

    @Schema(description = "编号")
	private String functionCode;

    @Schema(description = "类型")
	private int type;

    @Schema(description = "排序")
	private int sort;

    @Schema(description = "功能名称")
	private String functionName;

    @Schema(description = "功能父级编号")
	private String parentId;

    @Schema(description = "请求路径")
	private String path;

    @Schema(description = "图标")
	private String icon;

    @Schema(hidden = true)
	private int status;

    @Schema(description = "是否菜单(默认0否，1是)")
	private boolean menuFlag;

    @Schema(description = "功能描述")
	private String description;

    @Schema(hidden = true)
	private String addUser;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(hidden = true)
	private Date addTime;
	
}

