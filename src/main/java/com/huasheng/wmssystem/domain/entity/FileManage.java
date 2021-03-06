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
@Schema(description = "文件管理")
@SQLDelete(sql = "update [FileManage] set status = -1 where file_manage_id = ?")
@Where(clause = "status <> -1")
public class FileManage implements Serializable {
   
    @Schema(description = "文件ID")
    @Id
	private String fileManageId;

    @Schema(description = "文件类型（P图片，F文件）")
	private String type;

    @Schema(description = "来源（比如：商户头像：V_HeadPic）")
	private String source;

    @Schema(description = "文件大小")
	private String size;

    @Schema(description = "本地地址")
	private String localPath;

    @Schema(description = "云盘地址")
	private String cloudPath;

    @Schema(description = "描述")
	private String desc;

    @Schema(hidden = true)
	private String addUser;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Schema(hidden = true)
	private Date addTime;
	
}

