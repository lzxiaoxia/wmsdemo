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
@Schema(description = "系统设置表")
@SQLDelete(sql = "update [SystemSetting] set status = -1 where system_set_id = ?")
@Where(clause = "status <> -1")
public class SystemSetting implements Serializable {
   
    @Schema(description = "系统设置ID")
    @Id
	private String systemSetId;

    @Schema(description = "类别")
	private String type;

    @Schema(description = "键")
	private String key;

    @Schema(description = "值")
	private int value;

    @Schema(description = "值")
	private String valueStr;

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
	
}

