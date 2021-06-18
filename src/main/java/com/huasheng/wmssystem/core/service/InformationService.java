package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.InformationRepository;
import com.huasheng.wmssystem.domain.entity.Information;
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
public class InformationService {

    @Autowired
    InformationRepository informationRepository;

    public boolean deleteById(String id) {
    
        informationRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteByIds(List<String> ids) {
        informationRepository.deleteByIds(ids);
        return true;
    }

    public Information findByInformationId(String informationId) {
        Information information = informationRepository.findById(informationId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        return information;
    }
    
    public boolean addOrEdit(Information bean) {
        if (Tools.isEmpty(bean.getInfoId())) {//新增
            bean.setInfoId(Tools.getUUID());

            bean.setAddTime(Tools.getNowTime());
        } else {
            Information oldBean = informationRepository.findById(bean.getInfoId()).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));
            
        }

        informationRepository.save(bean);

        return true;
    }

    /**
     * 分页查询
     */
    public ListResult<List<Information>> findList(ListPara listPara) {

        Pageable pageable = PageRequest.of(listPara.getPage() - 1, listPara.getPageSize(), Sort.Direction.DESC, "addTime");
        Page<Information> resultPage = informationRepository.findAll(new Specification<Information>() {
            @Override
            public Predicate toPredicate(Root<Information> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
//                if (!Tools.isEmpty(nameParam)) {
//                    list.add(criteriaBuilder.like(root.get("nameParam").as(String.class), "%" + nameParam + "%"));
//                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);

        ListResult<Information> listResult = new ListResult<>();
        return listResult.succ(resultPage);
    }


}

