package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.FileManage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FileManageRepository extends JpaRepository<FileManage, String>, JpaSpecificationExecutor<FileManage> {

    @Query(value = "update [FileManage] set status = -1 where file_manage_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

}
