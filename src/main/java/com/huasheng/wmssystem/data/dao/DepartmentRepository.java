package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, String>, JpaSpecificationExecutor<Department> {

    @Query(value = "update [Department] set status = -1 where department_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

}
