package com.huasheng.wmssystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//@Getter
//@Setter
@Entity
@Table(name = "test")
@Data
@SQLDelete(sql = "update [test] set status = -1 where rid = ?")
@Where(clause = "status <> -1")
public class Test {

    @Id
//    @Column(name = "RId" , columnDefinition="uniqueidentifier")
//    @GeneratedValue(generator = "jpa-uuid")
//    @Column(length = 32)
    @ApiModelProperty(required = true)
    private String rid;

    @Column(name = "rolename")
    private String roleName;

//    @Column(name = "description" , columnDefinition="nvarchar")
    @ApiModelProperty(value = "描述",example = "描述")
    private String description;

    @Column(name = "padlogin")
    private boolean padLogin;

    @Column(name = "padloginaisle")
//    @Column(name = "padloginaisle" , columnDefinition="uniqueidentifier")
    private String padLoginAisle;

    private int status;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    @ApiModelProperty(hidden = true)
//    @ApiModelProperty(dataType = "Date" )
    @Column(name = "add_time")
    private Date addTime;

    @Column(name = "adduser", columnDefinition = "uniqueidentifier")
    @ApiModelProperty(hidden = true)
    private String addUser;

}
