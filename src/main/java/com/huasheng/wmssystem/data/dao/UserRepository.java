package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/14 16:00
 * @Description ：
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    User findByUserId(String userId);

    User findByUsername(String username);

}
