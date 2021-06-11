package com.huasheng.wmssystem.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/18 10:37
 * @Description ：文件管理
 */
@Data
public class FileManage {

    @Id
    private String fileManageId;


    private String source;

    private String size;

    private int type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Timestamp addTime;

    private String addUser;


}
