package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FunctionRepository extends JpaRepository<Function, String>, JpaSpecificationExecutor<Function> {

    @Query(value = "update [Function] set status = -1 where function_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

}
