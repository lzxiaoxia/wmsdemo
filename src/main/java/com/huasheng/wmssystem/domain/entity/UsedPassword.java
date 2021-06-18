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
@Schema(description = "已用历史密码表")
@SQLDelete(sql = "update [UsedPassword] set status = -1 where used_pwd_id = ?")
@Where(clause = "status <> -1")
public class UsedPassword implements Serializable {
   
    @Schema(description = "表ID")
    @Id
	private String usedPwdId;

    @Schema(description = "密码")
	private String password;

    @Schema(description = "使用次数")
	private int count;

    @Schema(description = "用户ID")
	private String userId;
	
}

