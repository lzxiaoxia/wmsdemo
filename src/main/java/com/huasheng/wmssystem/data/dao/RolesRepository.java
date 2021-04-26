package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/*
JpaRepository 实现基本查询
JpaSpecificationExecutor 主要分页查询用到
        */
@Repository
public interface RolesRepository extends JpaRepository<Roles, String>, JpaSpecificationExecutor<Roles> {

    Roles findByRid(String rid);

    Roles findByNameAndStatus(String name, int status);


}
