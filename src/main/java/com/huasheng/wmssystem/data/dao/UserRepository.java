package com.huasheng.wmssystem.data.dao;

import com.huasheng.wmssystem.domain.entity.User;
import com.huasheng.wmssystem.domain.model.resultmodel.web.UserListResult;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Map;

public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    User findByUsername(String username);

    @Query(value = "update [User] set status = -1 where user_id in (?1)", nativeQuery = true)
    @Modifying
    public void deleteByIds(List<String> ids);

   /* @Query(value = " SELECT  ar.id, ar.author, su.avatar,ar.user_id as userId, ar.title, ar.image_url as imageUrl, ar.create_date as createDate, ar.lastmodified_time as lastmodifiedTime, " +
            "  ar.tag, ar.tag_id as tagId,ar.des,ar.status, arr.readTimes,alr.likeRecordTimes,alra.checklike,aco.commentTimes FROM article ar " +
            " LEFT JOIN (SELECT a.id,COUNT(rr.article) as readTimes FROM article a LEFT JOIN read_record rr ON rr.article = a.id GROUP BY a.id)  arr ON arr.id = ar.id " +
            " LEFT JOIN  sys_user su ON su.id = ar.user_id WHERE ar.status =:status and ar.tag_id =:tagId ",
            countQuery = "SELECT count(*) FROM article",
            nativeQuery = true)
    Page<Map<String, Object>> findUserList(@Param("username") String username, @Param("status") int status, @Param("tagId") int tagId, Pageable pageable);*/

}
