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
@Schema(description = "系统日志表")
@SQLDelete(sql = "update [SystemLog] set status = -1 where system_log_id = ?")
@Where(clause = "status <> -1")
public class SystemLog implements Serializable {
   
    @Schema(description = "系统日志ID")
    @Id
	private String systemLogId;

    @Schema(description = "IP地址")
	private String ip;

    @Schema(description = "操作内容")
	private String content;

    @Schema(description = "日志类型")
	private int type;

    @Schema(description = "客户端识别")
	private String client;

    @Schema(hidden = true)
	private String addUser;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(hidden = true)
	private Date addTime;
	
}

