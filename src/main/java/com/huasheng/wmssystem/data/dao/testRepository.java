package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/*
JpaRepository 实现基本查询
JpaSpecificationExecutor 主要分页查询用到
        */
//@Repository
public interface testRepository extends JpaRepository<Test, String>, JpaSpecificationExecutor<Test> {

    Test findByRid(String rid);

    Test findByRoleNameAndStatus(String roleName, int status);


}
