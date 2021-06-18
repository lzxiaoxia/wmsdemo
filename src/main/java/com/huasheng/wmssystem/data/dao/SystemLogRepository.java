package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.SystemLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemLogRepository extends JpaRepository<SystemLog, String>, JpaSpecificationExecutor<SystemLog> {

    @Query(value = "update [SystemLog] set status = -1 where system_log_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

}
