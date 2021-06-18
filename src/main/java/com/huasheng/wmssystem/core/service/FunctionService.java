package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.FunctionRepository;
import com.huasheng.wmssystem.domain.entity.Function;
import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import com.huasheng.wmssystem.domain.model.paramodel.web.FunctionListPara;
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
public class FunctionService {

    @Autowired
    FunctionRepository functionRepository;

    public boolean deleteById(String id) {
    
        functionRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteByIds(List<String> ids) {
        functionRepository.deleteByIds(ids);
        return true;
    }

    public Function findByFunctionId(String functionId) {
        Function function = functionRepository.findById(functionId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        return function;
    }
    
    public boolean addOrEdit(Function bean) {
        if (Tools.isEmpty(bean.getFunctionId())) {//新增
            bean.setFunctionId(Tools.getUUID());

            bean.setAddTime(Tools.getNowTime());
        } else {
            Function oldBean = functionRepository.findById(bean.getFunctionId()).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));
            
        }

        functionRepository.save(bean);

        return true;
    }

    /**
     * 分页查询
     */
    public ListResult<List<Function>> findList(FunctionListPara listPara) {

        Pageable pageable = PageRequest.of(listPara.getPage() - 1, listPara.getPageSize(), Sort.Direction.DESC, "addTime");
        Page<Function> resultPage = functionRepository.findAll(new Specification<Function>() {
            @Override
            public Predicate toPredicate(Root<Function> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!Tools.isEmpty(listPara.getFunctionName())) {
                    list.add(criteriaBuilder.like(root.get("functionName").as(String.class), "%" + listPara.getFunctionName() + "%"));
                }
                if (!Tools.isEmpty(listPara.getFunctionCode())) {
                    list.add(criteriaBuilder.like(root.get("functionCode").as(String.class), "%" + listPara.getFunctionCode() + "%"));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);

        ListResult<Function> listResult = new ListResult<>();
        return listResult.succ(resultPage);
    }


}

