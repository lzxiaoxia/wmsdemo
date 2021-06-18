package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.RoleFunctionMappingRepository;
import com.huasheng.wmssystem.domain.entity.RoleFunctionMapping;
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
public class RoleFunctionMappingService {

    @Autowired
    RoleFunctionMappingRepository roleFunctionMappingRepository;

    public boolean deleteById(String id) {
    
        roleFunctionMappingRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteByIds(List<String> ids) {
        roleFunctionMappingRepository.deleteByIds(ids);
        return true;
    }

    public RoleFunctionMapping findByRoleFunctionMappingId(String roleFunctionMappingId) {
        RoleFunctionMapping roleFunctionMapping = roleFunctionMappingRepository.findById(roleFunctionMappingId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        return roleFunctionMapping;
    }
    
    public boolean addOrEdit(RoleFunctionMapping bean) {
        if (Tools.isEmpty(bean.getRoleFunctionId())) {//新增
            bean.setRoleFunctionId(Tools.getUUID());

            bean.setAddTime(Tools.getNowTime());
        } else {
            RoleFunctionMapping oldBean = roleFunctionMappingRepository.findById(bean.getRoleFunctionId()).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));
            
        }

        roleFunctionMappingRepository.save(bean);

        return true;
    }

    /**
     * 分页查询
     */
    public ListResult<List<RoleFunctionMapping>> findList(ListPara listPara) {

        Pageable pageable = PageRequest.of(listPara.getPage() - 1, listPara.getPageSize(), Sort.Direction.DESC, "addTime");
        Page<RoleFunctionMapping> resultPage = roleFunctionMappingRepository.findAll(new Specification<RoleFunctionMapping>() {
            @Override
            public Predicate toPredicate(Root<RoleFunctionMapping> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
//                if (!Tools.isEmpty(nameParam)) {
//                    list.add(criteriaBuilder.like(root.get("nameParam").as(String.class), "%" + nameParam + "%"));
//                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);

        ListResult<RoleFunctionMapping> listResult = new ListResult<>();
        return listResult.succ(resultPage);
    }


}

