package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.UsedPassword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UsedPasswordRepository extends JpaRepository<UsedPassword, String>, JpaSpecificationExecutor<UsedPassword> {

    @Query(value = "update [UsedPassword] set status = -1 where used_pwd_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

}
