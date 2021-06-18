package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.Information;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InformationRepository extends JpaRepository<Information, String>, JpaSpecificationExecutor<Information> {

    @Query(value = "update [Information] set status = -1 where info_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

}
