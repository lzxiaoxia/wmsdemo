package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.SystemSettingRepository;
import com.huasheng.wmssystem.domain.entity.SystemSetting;
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
public class SystemSettingService {

    @Autowired
    SystemSettingRepository systemSettingRepository;

    public boolean deleteById(String id) {
    
        systemSettingRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteByIds(List<String> ids) {
        systemSettingRepository.deleteByIds(ids);
        return true;
    }

    public SystemSetting findBySystemSettingId(String systemSettingId) {
        SystemSetting systemSetting = systemSettingRepository.findById(systemSettingId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        return systemSetting;
    }
    
    public boolean addOrEdit(SystemSetting bean) {
        if (Tools.isEmpty(bean.getSystemSetId())) {//新增
            bean.setSystemSetId(Tools.getUUID());

            bean.setAddTime(Tools.getNowTime());
        } else {
            SystemSetting oldBean = systemSettingRepository.findById(bean.getSystemSetId()).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));
            
        }

        systemSettingRepository.save(bean);

        return true;
    }

    /**
     * 分页查询
     */
    public ListResult<List<SystemSetting>> findList(ListPara listPara) {

        Pageable pageable = PageRequest.of(listPara.getPage() - 1, listPara.getPageSize(), Sort.Direction.DESC, "addTime");
        Page<SystemSetting> resultPage = systemSettingRepository.findAll(new Specification<SystemSetting>() {
            @Override
            public Predicate toPredicate(Root<SystemSetting> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
//                if (!Tools.isEmpty(nameParam)) {
//                    list.add(criteriaBuilder.like(root.get("nameParam").as(String.class), "%" + nameParam + "%"));
//                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);

        ListResult<SystemSetting> listResult = new ListResult<>();
        return listResult.succ(resultPage);
    }


}

