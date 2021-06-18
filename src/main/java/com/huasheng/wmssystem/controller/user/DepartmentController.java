package com.huasheng.wmssystem.controller.user;

import com.huasheng.wmssystem.core.service.DepartmentService;
import com.huasheng.wmssystem.domain.entity.Department;
import com.huasheng.wmssystem.domain.model.paramodel.web.DepartmentListPara;
import com.huasheng.wmssystem.domain.model.resultmodel.DataResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ListResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ResultBase;
import com.huasheng.wmssystem.exception.CommonErrorEnums;
import com.huasheng.wmssystem.exception.CustomException;
import com.huasheng.wmssystem.shiro.AccountProfile;
import com.huasheng.wmssystem.utils.Constant;
import com.huasheng.wmssystem.utils.JwtUtils;
import com.huasheng.wmssystem.utils.Tools;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/department")
@Tag(name = "DepartmentController", description = "部门管理表接口")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("getById/{id}")
    public DataResult<Department> getById(@PathVariable @Pattern(regexp = Constant.uuidRegex, message = "id不规范") String id) {
        Department department = departmentService.findByDepartmentId(id);

        DataResult<Department> dataResult = new DataResult<>();
        return dataResult.succ(department);
    }

    @PostMapping("getList")
    public ListResult<List<Department>> list(DepartmentListPara listPara) {
        return departmentService.findList(listPara);
    }

    @PostMapping("add")
    public ResultBase addDepartment(@RequestBody Department bean) {
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        bean.setAddUser(accountProfile.getUserId());
        departmentService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @PutMapping("edit")
    public ResultBase editDepartment(@RequestBody Department bean) {
        if (!Tools.checkUuid(bean.getDepartmentId()))
            throw new CustomException(CommonErrorEnums.UUID_ERROR);

        departmentService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @DeleteMapping("delete")
    public ResultBase delete(@Pattern(regexp = Constant.uuidRegex, message = "id不规范") String id) {
        departmentService.deleteById(id);
        return ResultBase.succ();
    }

    @PostMapping("deleteBatch")
    public ResultBase deleteBatch(@RequestBody List<String> ids) {
        departmentService.deleteByIds(ids);
        return ResultBase.succ();
    }

}

