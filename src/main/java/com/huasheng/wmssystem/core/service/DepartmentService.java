package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.DepartmentRepository;
import com.huasheng.wmssystem.domain.entity.Department;
import com.huasheng.wmssystem.domain.model.paramodel.web.DepartmentListPara;
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
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    public boolean deleteById(String id) {

        departmentRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteByIds(List<String> ids) {
        departmentRepository.deleteByIds(ids);
        return true;
    }

    public Department findByDepartmentId(String departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        return department;
    }

    public boolean addOrEdit(Department bean) {
        if (Tools.isEmpty(bean.getDepartmentId())) {//新增
            bean.setDepartmentId(Tools.getUUID());

            bean.setAddTime(Tools.getNowTime());
        } else {
            Department oldBean = departmentRepository.findById(bean.getDepartmentId()).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        }

        departmentRepository.save(bean);

        return true;
    }

    /**
     * 分页查询
     */
    public ListResult<List<Department>> findList(DepartmentListPara listPara) {

        Pageable pageable = PageRequest.of(listPara.getPage() - 1, listPara.getPageSize(), Sort.Direction.DESC, "addTime");
        Page<Department> resultPage = departmentRepository.findAll(new Specification<Department>() {
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!Tools.isEmpty(listPara.getDepartmentName())) {
                    list.add(criteriaBuilder.like(root.get("departmentName").as(String.class), "%" + listPara.getDepartmentName() + "%"));
                }
                if (!Tools.isEmpty(listPara.getDescription())) {
                    list.add(criteriaBuilder.like(root.get("description").as(String.class), "%" + listPara.getDescription() + "%"));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);

        ListResult<Department> listResult = new ListResult<>();
        return listResult.succ(resultPage);
    }


}

