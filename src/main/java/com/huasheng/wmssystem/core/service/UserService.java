package com.huasheng.wmssystem.core.service;

import cn.hutool.core.bean.BeanUtil;
import com.huasheng.wmssystem.data.dao.UserRepository;
import com.huasheng.wmssystem.domain.entity.*;
import com.huasheng.wmssystem.domain.entity.QDepartment;
import com.huasheng.wmssystem.domain.entity.QRoles;
import com.huasheng.wmssystem.domain.entity.QUser;
import com.huasheng.wmssystem.domain.entity.QUserRolesMapping;
import com.huasheng.wmssystem.domain.model.paramodel.web.UserListPara;
import com.huasheng.wmssystem.domain.model.resultmodel.IdAndName;
import com.huasheng.wmssystem.domain.model.resultmodel.ListResult;
import com.huasheng.wmssystem.domain.model.resultmodel.web.UserInfoResult;
import com.huasheng.wmssystem.domain.model.resultmodel.web.UserListResult;
import com.huasheng.wmssystem.exception.CommonErrorEnums;
import com.huasheng.wmssystem.exception.NotFoundException;
import com.huasheng.wmssystem.utils.SpringUtils;
import com.huasheng.wmssystem.utils.Tools;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
//    @Autowired
//    DepartmentService departmentService;
    @Autowired
    RolesService rolesService;

    public User findByUserName(String username) {
        User user = userRepository.findByUsername(username);

        return user;
    }

    public boolean deleteById(String id) {
        userRepository.deleteById(id);
        return true;
    }

    @Transactional
    public boolean deleteByIds(List<String> ids) {
        userRepository.deleteByIds(ids);
        return true;
    }

    public User findByUserId(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

        return user;
    }

    public UserInfoResult getUserInfo(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));


        DepartmentService departmentService = SpringUtils.getBean(DepartmentService.class);
        Department department = departmentService.findByDepartmentId(user.getDepartmentId());

//        RolesService rolesService = SpringUtils.getBean(RolesService.class);
        List<Roles> roles = rolesService.findAnyByUserId(userId);

        UserInfoResult userInfoResult = new UserInfoResult();
        BeanUtil.copyProperties(user, userInfoResult);

        userInfoResult.setDepartmentName(department.getDepartmentName());

        List<IdAndName> userRole=new ArrayList<>();
        roles.forEach(item->userRole.add(new IdAndName(item.getRoleId(),item.getRoleName())));
        userInfoResult.setRole(userRole);
        return userInfoResult;
    }

    public boolean addOrEdit(User bean) {
        if (Tools.isEmpty(bean.getUserId())) {//新增
            bean.setUserId(Tools.getUUID());

            bean.setAddTime(Tools.getNowTime());
            bean.setEditTime(Tools.getNowTime());

            userRepository.save(bean);
        } else {
            User oldBean = userRepository.findById(bean.getUserId()).orElseThrow(() -> new NotFoundException(CommonErrorEnums.ENTITY_NOT_FOUND_ERROR));

            oldBean.setUsername(bean.getUsername());
            oldBean.setUserNumber(bean.getUserNumber());
            oldBean.setRealName(bean.getRealName());
            oldBean.setPhoneNumber(bean.getPhoneNumber());
            oldBean.setDepartmentId(bean.getDepartmentId());
            oldBean.setFixedPhone(bean.getFixedPhone());
            oldBean.setEmail(bean.getEmail());

            userRepository.save(oldBean);
        }


        return true;
    }

    @Autowired
    private EntityManager entityManager;
    //查询工厂实体
    private JPAQueryFactory queryFactory;

    //实例化控制器完成后执行该方法实例化JPAQueryFactory
    @PostConstruct
    public void initFactory() {
        queryFactory = new JPAQueryFactory(entityManager);
    }

    /**
     * 分页查询
     */
    public ListResult<List<UserListResult>> findList(UserListPara listPara) {
        Pageable pageable = PageRequest.of(listPara.getPage() - 1, listPara.getPageSize(), Sort.Direction.DESC, "addTime");

      /*  Page<User> resultPage = userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();
                if (!Tools.isEmpty(listPara.getUsername())) {
                    list.add(criteriaBuilder.like(root.get("username").as(String.class), "%" + listPara.getUsername() + "%"));
                }
                if (!Tools.isEmpty(listPara.getUserNumber())) {
                    list.add(criteriaBuilder.like(root.get("userNumber").as(String.class), "%" + listPara.getUserNumber() + "%"));
                }
                //创建左外连接  Join<左，右>     root.join("副表实体在主表主体中的属性名"，连接方式)
//                Join<User, Department> join = root.join("departmentId", JoinType.LEFT);
                //将连接表需要查询的字段写入
//                list.add(criteriaBuilder.like(join.get("post").as(String.class),"%"+key+"%"));

                Predicate[] p = new Predicate[list.size()];
                return criteriaBuilder.and(list.toArray(p));
            }
        }, pageable);*/

        

        QUser qUser = QUser.user;
        QDepartment qDepartment = QDepartment.department;
        QRoles qRoles = QRoles.roles;
        QUserRolesMapping qUserRolesMapping = QUserRolesMapping.userRolesMapping;

        JPAQuery<UserListResult> jpaQuery = queryFactory.select(Projections.bean(
                UserListResult.class,
                qUser.userId, qUser.username, qUser.userNumber, qUser.realName, qUser.departmentId,
                qUser.phoneNumber, qUser.fixedPhone, qUser.addTime, qUser.description, qUser.email,
                qDepartment.departmentName,
                qRoles.roleId, qRoles.roleName
        ))
                .from(qUser)
                .leftJoin(qDepartment).on(qUser.departmentId.eq(qDepartment.departmentId))
                .leftJoin(qUserRolesMapping).on(qUser.userId.eq(qUserRolesMapping.userId))
                .leftJoin(qRoles).on(qUserRolesMapping.roleId.eq(qRoles.roleId))
                .orderBy(qUser.addTime.desc())
                .offset((long) listPara.getPageSize() * (listPara.getPage() - 1))
                .limit(listPara.getPageSize());

        if (!Tools.isEmpty(listPara.getUsername()))
            jpaQuery.where(qUser.username.like("%" + listPara.getUsername() + "%"));

        if (!Tools.isEmpty(listPara.getUserNumber()))
            jpaQuery.where(qUser.userNumber.like("%" + listPara.getUserNumber() + "%"));

        if (!Tools.isEmpty(listPara.getDepartmentId()))
            jpaQuery.where(qUser.departmentId.eq(listPara.getDepartmentId()));

        List<UserListResult> userListResults = jpaQuery.fetch();

        long total = jpaQuery.fetchCount();
        int pageTotal = (int) (total == 0 ? 0 : total / listPara.getPageSize() + 1);

        ListResult<UserListResult> listResult = new ListResult<>();


        return listResult.succ(userListResults, total, pageTotal);
    }


}

