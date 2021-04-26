package com.huasheng.wmssystem.core.service;


import com.huasheng.wmssystem.domain.entity.Roles;
import com.huasheng.wmssystem.data.dao.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class RolesService {

    @Autowired
    RolesRepository roleRepository;

    /**
     * 分页查询
     */
    public Page findList(String nameParam, int pageNum, int pageSize){

        Pageable pageable= PageRequest.of(pageNum,pageSize, Sort.Direction.DESC,"addTime");
        Page<Roles> rolesPage=roleRepository.findAll(new Specification<Roles>() {
            @Override
            public Predicate toPredicate(Root<Roles> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list=new ArrayList<>();
                if(!StringUtils.isEmpty(nameParam))
                {
                    list.add(criteriaBuilder.like(root.get("roleName").as(String.class), "%"+nameParam+"%"));
                }

                list.add(criteriaBuilder.equal(root.get("delFlag").as(Boolean.class),false));

                Predicate[] p=new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        },pageable);

        return rolesPage;
    }

    /**
     * @Description
     * @Param       [rid]
     * @return      com.huasheng.wmssystem.domain.entity.Roles
     * @Author      xjTang
     * @Date        Create by 2021/4/25 15:53
     */
    public Roles findByRid(String rid){
        Roles roles=roleRepository.findByRid(rid);

        return roles;
    }

    /**
     * @Description 根据名称查询启用角色
     * @Param       [name]
     * @return      com.huasheng.wmssystem.domain.entity.Roles
     * @Author      xjTang
     * @Date        Create by 2021/4/25 16:12
     */
    public Roles findByName(String name){
        Roles roles=roleRepository.findByNameAndStatus(name,1);

        return roles;

    }





}
