package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.UserRepository;
import com.huasheng.wmssystem.data.dao.testRepository;
import com.huasheng.wmssystem.domain.entity.Test;
import com.huasheng.wmssystem.domain.entity.User;
import com.huasheng.wmssystem.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserService{

    @Autowired
    UserRepository userRepository;


    public User findByUserId(String userId){
        User user = userRepository.findByUserId(userId);

        return user;
    }

    public User findByUserName(String username){
        User user = userRepository.findByUsername(username);

        return user;
    }


    /**
     * 分页查询
     */
    public Page<User> findList(String nameParam, int pageNum, int pageSize) {

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "addTime");

        return userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!Tools.isEmpty(nameParam)) {
                    list.add(criteriaBuilder.like(root.get("roleName").as(String.class), "%" + nameParam + "%"));
                }

//                list.add(criteriaBuilder.equal(root.get("delFlag").as(Boolean.class),false));

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);
    }

}
