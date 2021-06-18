package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.UserRolesMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRolesMappingRepository extends JpaRepository<UserRolesMapping, String>, JpaSpecificationExecutor<UserRolesMapping> {

    @Query(value = "update [UserRolesMapping] set status = -1 where user_role_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

    public List<UserRolesMapping> findAllByUserId(String userId);

}
