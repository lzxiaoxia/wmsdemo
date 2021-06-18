package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.UserRolesMappingRepository;
import com.huasheng.wmssystem.domain.entity.UserRolesMapping;
import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import com.huasheng.wmssystem.domain.model.resultmodel.ListResult;
import com.huasheng.wmssystem.exception.CommonErrorEnums;
import com.huasheng.wmssystem.exception.NotFoundException;
import com.huasheng.wmssystem.utils.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserRolesMappingService {

    @Autowired
    UserRolesMappingRepository userRolesMappingRepository;

    public boolean deleteById(String id) {
    
        userRolesMappingRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteByIds(List<String> ids) {
        userRolesMappingRepository.deleteByIds(ids);
        return true;
    }

    public UserRolesMapping findByUserRolesMappingId(String userRolesMappingId) {
        UserRolesMapping userRolesMapping = userRolesMappingRepository.findById(userRolesMappingId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        return userRolesMapping;
    }

    public List<UserRolesMapping> findAllByUserId(String userId){
        return userRolesMappingRepository.findAllByUserId(userId);
    }




}

