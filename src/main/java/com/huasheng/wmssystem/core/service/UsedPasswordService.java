package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.UsedPasswordRepository;
import com.huasheng.wmssystem.domain.entity.UsedPassword;
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
public class UsedPasswordService {

    @Autowired
    UsedPasswordRepository usedPasswordRepository;

    public boolean deleteById(String id) {
    
        usedPasswordRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteByIds(List<String> ids) {
        usedPasswordRepository.deleteByIds(ids);
        return true;
    }

    public UsedPassword findByUsedPasswordId(String usedPasswordId) {
        UsedPassword usedPassword = usedPasswordRepository.findById(usedPasswordId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        return usedPassword;
    }
    
    public boolean addOrEdit(UsedPassword bean) {
        if (Tools.isEmpty(bean.getUsedPwdId())) {//新增
            bean.setUsedPwdId(Tools.getUUID());

        } else {
            UsedPassword oldBean = usedPasswordRepository.findById(bean.getUsedPwdId()).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));
            
        }

        usedPasswordRepository.save(bean);

        return true;
    }

    /**
     * 分页查询
     */
    public ListResult<List<UsedPassword>> findList(ListPara listPara) {

        Pageable pageable = PageRequest.of(listPara.getPage() - 1, listPara.getPageSize(), Sort.Direction.DESC, "addTime");
        Page<UsedPassword> resultPage = usedPasswordRepository.findAll(new Specification<UsedPassword>() {
            @Override
            public Predicate toPredicate(Root<UsedPassword> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
//                if (!Tools.isEmpty(nameParam)) {
//                    list.add(criteriaBuilder.like(root.get("nameParam").as(String.class), "%" + nameParam + "%"));
//                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);

        ListResult<UsedPassword> listResult = new ListResult<>();
        return listResult.succ(resultPage);
    }


}

