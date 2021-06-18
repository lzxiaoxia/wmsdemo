package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.RoleFunctionMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleFunctionMappingRepository extends JpaRepository<RoleFunctionMapping, String>, JpaSpecificationExecutor<RoleFunctionMapping> {

    @Query(value = "update [RoleFunctionMapping] set status = -1 where role_function_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

}
