package com.huasheng.wmssystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;


//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
@Data
@Entity
@Table(name = "[User]")
@ApiModel(value = "")
public class User implements Serializable {

//    private static final long serialVersionUID = 1L;

    //    @TableId(value = "id", type = IdType.AUTO)
    @Id
    private String userId;

    @NotBlank(message = "昵称不能为空")
    @ApiModelProperty(value = "")
    private String username;

    private String realName;

    private String phoneNumber;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    private String password;

    private boolean lockFlag;

    private boolean forgetFlag;

    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private java.sql.Date addTime;

    private String addUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date editTime;

    private String editUser;


}
