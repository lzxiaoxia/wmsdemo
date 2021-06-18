package com.huasheng.wmssystem.controller.user;

import com.huasheng.wmssystem.core.service.RolesService;
import com.huasheng.wmssystem.domain.entity.Roles;
import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import com.huasheng.wmssystem.domain.model.paramodel.web.RolesListPara;
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
@RequestMapping("/api/roles")
@Tag(name = "RolesController", description = "角色表接口")
public class RolesController {

    @Autowired
    RolesService rolesService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("getById/{id}")
    public DataResult<Roles> getById(@PathVariable @Pattern(regexp = Constant.uuidRegex, message = "id不规范") String id) {
        Roles roles = rolesService.findByRolesId(id);

        DataResult<Roles> dataResult = new DataResult<>();
        return dataResult.succ(roles);
    }

    @PostMapping("getList")
    public ListResult<List<Roles>> list(RolesListPara listPara) {
        return rolesService.findList(listPara);
    }

    @PostMapping("add")
    public ResultBase addRoles(@RequestBody Roles bean) {
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        bean.setAddUser(accountProfile.getUserId());
        rolesService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @PutMapping("edit")
    public ResultBase editRoles(@RequestBody Roles bean) {
        if (!Tools.checkUuid(bean.getRoleId()))
            throw new CustomException(CommonErrorEnums.UUID_ERROR);

        rolesService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @DeleteMapping("delete")
    public ResultBase delete(@Pattern(regexp = Constant.uuidRegex, message = "id不规范") String id) {
        rolesService.deleteById(id);
        return ResultBase.succ();
    }

    @PostMapping("deleteBatch")
    public ResultBase deleteBatch(@RequestBody List<String> ids) {
        rolesService.deleteByIds(ids);
        return ResultBase.succ();
    }

}

