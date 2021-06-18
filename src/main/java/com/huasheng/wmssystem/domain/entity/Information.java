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
@Schema(description = "消息通知表")
@SQLDelete(sql = "update [Information] set status = -1 where info_id = ?")
@Where(clause = "status <> -1")
public class Information implements Serializable {
   
    @Schema(description = "系统日志ID")
    @Id
	private String infoId;

    @Schema(description = "标题")
	private String title;

    @Schema(description = "内容")
	private String content;

    @Schema(description = "消息类型")
	private int type;

    @Schema(hidden = true)
	private int status;

    @Schema(hidden = true)
	private String addUser;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(hidden = true)
	private Date addTime;
	
}

