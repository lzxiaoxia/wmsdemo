package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.SystemSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemSettingRepository extends JpaRepository<SystemSetting, String>, JpaSpecificationExecutor<SystemSetting> {

    @Query(value = "update [SystemSetting] set status = -1 where system_set_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

}
