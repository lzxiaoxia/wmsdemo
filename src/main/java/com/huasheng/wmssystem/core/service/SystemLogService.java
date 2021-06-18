package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.SystemLogRepository;
import com.huasheng.wmssystem.domain.entity.SystemLog;
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
public class SystemLogService {

    @Autowired
    SystemLogRepository systemLogRepository;

    public boolean deleteById(String id) {
    
        systemLogRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteByIds(List<String> ids) {
        systemLogRepository.deleteByIds(ids);
        return true;
    }

    public SystemLog findBySystemLogId(String systemLogId) {
        SystemLog systemLog = systemLogRepository.findById(systemLogId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        return systemLog;
    }
    
    public boolean addOrEdit(SystemLog bean) {
        if (Tools.isEmpty(bean.getSystemLogId())) {//新增
            bean.setSystemLogId(Tools.getUUID());

            bean.setAddTime(Tools.getNowTime());
        } else {
            SystemLog oldBean = systemLogRepository.findById(bean.getSystemLogId()).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));
            
        }

        systemLogRepository.save(bean);

        return true;
    }

    /**
     * 分页查询
     */
    public ListResult<List<SystemLog>> findList(ListPara listPara) {

        Pageable pageable = PageRequest.of(listPara.getPage() - 1, listPara.getPageSize(), Sort.Direction.DESC, "addTime");
        Page<SystemLog> resultPage = systemLogRepository.findAll(new Specification<SystemLog>() {
            @Override
            public Predicate toPredicate(Root<SystemLog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
//                if (!Tools.isEmpty(nameParam)) {
//                    list.add(criteriaBuilder.like(root.get("nameParam").as(String.class), "%" + nameParam + "%"));
//                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);

        ListResult<SystemLog> listResult = new ListResult<>();
        return listResult.succ(resultPage);
    }


}

