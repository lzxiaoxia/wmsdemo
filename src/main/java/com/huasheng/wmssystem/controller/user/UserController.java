package com.huasheng.wmssystem.controller.user;

import com.huasheng.wmssystem.core.service.UserService;
import com.huasheng.wmssystem.domain.entity.User;
import com.huasheng.wmssystem.domain.model.paramodel.web.UserListPara;
import com.huasheng.wmssystem.domain.model.resultmodel.DataResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ListResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ResultBase;
import com.huasheng.wmssystem.domain.model.resultmodel.web.UserInfoResult;
import com.huasheng.wmssystem.domain.model.resultmodel.web.UserListResult;
import com.huasheng.wmssystem.exception.CommonErrorEnums;
import com.huasheng.wmssystem.exception.CustomException;
import com.huasheng.wmssystem.utils.Constant;
import com.huasheng.wmssystem.shiro.AccountProfile;
import com.huasheng.wmssystem.utils.JwtUtils;
import com.huasheng.wmssystem.utils.Tools;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.constraints.Pattern;

@RestController
@Validated
@RequestMapping("/api/user")
@Tag(name = "UserController", description = "用户表接口")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("getById/{id}")
    public DataResult<UserInfoResult> getById(@PathVariable @Pattern(regexp = Constant.uuidRegex,message = "id不规范") String id) {
        UserInfoResult userInfo = userService.getUserInfo(id);

        DataResult<UserInfoResult> dataResult = new DataResult<>();
        return dataResult.succ(userInfo);
    }

    @PostMapping("getList")
    public ListResult<List<UserListResult>> list(@RequestBody UserListPara listPara) {

        return userService.findList(listPara);
    }

    @PostMapping("add")
    public ResultBase addUser(@RequestBody User bean) {
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        bean.setAddUser(accountProfile.getUserId());
        bean.setEditUser(accountProfile.getUserId());
        userService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @PutMapping("edit")
    public ResultBase editUser(@RequestBody User bean) {
        if (!Tools.checkUuid(bean.getUserId()))
            throw new CustomException(CommonErrorEnums.UUID_ERROR);
            
        userService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @DeleteMapping("delete")
    public ResultBase delete(@Pattern(regexp = Constant.uuidRegex,message = "id不规范") String id) {
        userService.deleteById(id);
        return ResultBase.succ();
    }

    @PostMapping("deleteBatch")
    public ResultBase deleteBatch(@RequestBody List<String> ids) {
        userService.deleteByIds(ids);
        return ResultBase.succ();
    }

}

