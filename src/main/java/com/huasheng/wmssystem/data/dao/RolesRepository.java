package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles, String>, JpaSpecificationExecutor<Roles> {

    @Query(value = "update [Roles] set status = -1 where role_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

}
