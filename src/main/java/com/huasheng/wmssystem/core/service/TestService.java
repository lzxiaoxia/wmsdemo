package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.testRepository;
import com.huasheng.wmssystem.domain.entity.Test;
import com.huasheng.wmssystem.exception.CommonErrorEnums;
import com.huasheng.wmssystem.utils.Tools;
import javassist.NotFoundException;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TestService {

    @Autowired
    testRepository testRepository;

    /**
     * 分页查询
     */
    public Page findList(String nameParam, int pageNum, int pageSize) {

        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.Direction.DESC, "addTime");
//        Pageable pageable= PageRequest.of(pageNum,pageSize, Sort.Direction.DESC,"rid");
        Page<Test> rolesPage = testRepository.findAll(new Specification<Test>() {
            @Override
            public Predicate toPredicate(Root<Test> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!Tools.isEmpty(nameParam)) {
//                    list.add(criteriaBuilder.like(root.get("roleName").as(String.class), "%" + nameParam + "%"));
//                    list.add(criteriaBuilder.equal(root.get("delFlag").as(Boolean.class), false));
                }

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);

        return rolesPage;
    }

    /**
     * @return com.huasheng.wmssystem.domain.Roles
     * @Description
     * @Param [rid]
     * @Author xjTang
     * @Date Create by 2021/4/25 15:53
     */
    public Test findByRid(String rid) {

        Test test = testRepository.findById(rid).orElse(null);


        return test;
    }

    public boolean deleteById(String id) {

        testRepository.deleteById(id);


        return true;
    }

    /**
     * @return com.huasheng.wmssystem.domain.Roles
     * @Description 根据名称查询启用角色
     * @Param [name]
     * @Author xjTang
     * @Date Create by 2021/4/25 16:12
     */
    public Test findByName(String name) {
        Test test = testRepository.findByRoleNameAndStatus(name, 1);

        return test;

    }

    /**
     * 新增或编辑点击保存
     *
     * @param bean
     * @return
     */
//    @Transactional
//    @Transactional  @Parameter(in = ParameterIn.DEFAULT, description = "Pet object that needs to be added to the store", required=true, schema=@Schema()) @Valid @RequestBody Pet body

    public Map edit(Test bean) {
        Map map = new HashMap();

        if (Tools.isEmpty(bean.getRid())) {//新增
            bean.setRid(Tools.getUUID());

//            bean.setAddTime(Tools.getNowTime());
            bean.setAddUser("CDD775A4-AEA7-4D8F-B7EC-8CF3B4E28A1D");
            bean.setPadLoginAisle("CDD775A4-AEA7-4D8F-B7EC-8CF3B4E28A1D");
            bean.setStatus(1);

        } else {
            Test idBean = testRepository.findByRid(bean.getRid());
        }

        bean = testRepository.save(bean);


        map.put("bean", bean);
        map.put("code", 1);
        map.put("msg", "成功");
        return map;
    }


}
