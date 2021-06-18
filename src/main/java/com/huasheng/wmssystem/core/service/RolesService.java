package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.RolesRepository;
import com.huasheng.wmssystem.data.dao.UserRolesMappingRepository;
import com.huasheng.wmssystem.domain.entity.Roles;
import com.huasheng.wmssystem.domain.entity.UserRolesMapping;
import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import com.huasheng.wmssystem.domain.model.paramodel.web.RolesListPara;
import com.huasheng.wmssystem.domain.model.resultmodel.ListResult;
import com.huasheng.wmssystem.exception.CommonErrorEnums;
import com.huasheng.wmssystem.exception.NotFoundException;
import com.huasheng.wmssystem.utils.SpringUtils;
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
import java.util.stream.Collectors;

@Service
public class RolesService {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    UserRolesMappingService userRolesMappingService;

    public boolean deleteById(String id) {

        rolesRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteByIds(List<String> ids) {
        rolesRepository.deleteByIds(ids);
        return true;
    }

    public Roles findByRolesId(String rolesId) {
        Roles roles = rolesRepository.findById(rolesId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        return roles;
    }

    public List<Roles> findAnyByUserId(String userId) {
        List<UserRolesMapping> userRolesMappings = userRolesMappingService.findAllByUserId(userId);

        List<Roles> roles = rolesRepository.findAllById(userRolesMappings.stream().map(UserRolesMapping::getRoleId).collect(Collectors.toList()));

        return roles;
    }

    public boolean addOrEdit(Roles bean) {
        if (Tools.isEmpty(bean.getRoleId())) {//新增
            bean.setRoleId(Tools.getUUID());

            bean.setEnable(true);
            bean.setAddTime(Tools.getNowTime());
            rolesRepository.save(bean);
        } else {
            Roles oldBean = rolesRepository.findById(bean.getRoleId()).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));
            oldBean.setRoleName(bean.getRoleName());
            oldBean.setDescription(bean.getDescription());

            rolesRepository.save(oldBean);
        }


        return true;
    }

    /**
     * 分页查询
     */
    public ListResult<List<Roles>> findList(RolesListPara listPara) {

        Pageable pageable = PageRequest.of(listPara.getPage() - 1, listPara.getPageSize(), Sort.Direction.DESC, "addTime");
        Page<Roles> resultPage = rolesRepository.findAll(new Specification<Roles>() {
            @Override
            public Predicate toPredicate(Root<Roles> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!Tools.isEmpty(listPara.getRoleName())) {
                    list.add(criteriaBuilder.like(root.get("roleName").as(String.class), "%" + listPara.getRoleName() + "%"));
                }
                if (!Tools.isEmpty(listPara.getDescription())) {
                    list.add(criteriaBuilder.like(root.get("description").as(String.class), "%" + listPara.getDescription() + "%"));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);

        ListResult<Roles> listResult = new ListResult<>();
        return listResult.succ(resultPage);
    }


}

