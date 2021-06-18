package com.huasheng.wmssystem.controller.user;

import com.huasheng.wmssystem.core.service.SystemSettingService;
import com.huasheng.wmssystem.domain.entity.SystemSetting;
import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import com.huasheng.wmssystem.domain.model.resultmodel.DataResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ListResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ResultBase;
import com.huasheng.wmssystem.exception.CommonErrorEnums;
import com.huasheng.wmssystem.exception.CustomException;
import com.huasheng.wmssystem.utils.Constant;
import com.huasheng.wmssystem.shiro.AccountProfile;
import com.huasheng.wmssystem.utils.JwtUtils;
import com.huasheng.wmssystem.utils.Tools;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.constraints.Pattern;

@RestController
@Validated
@RequestMapping("/api/systemSetting")
@Tag(name = "SystemSettingController", description = "系统设置表接口")
public class SystemSettingController {

    @Autowired
    SystemSettingService systemSettingService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("getById/{id}")
    public DataResult<SystemSetting> getById(@PathVariable @Pattern(regexp = Constant.uuidRegex,message = "id不规范") String id) {
        SystemSetting systemSetting = systemSettingService.findBySystemSettingId(id);

        DataResult<SystemSetting> dataResult = new DataResult<>();
        return dataResult.succ(systemSetting);
    }

    @PostMapping("getList")
    public ListResult<List<SystemSetting>> list(ListPara listPara) {
        return systemSettingService.findList(listPara);
    }

    @PostMapping("add")
    public ResultBase addSystemSetting(@RequestBody SystemSetting bean) {
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        bean.setAddUser(accountProfile.getUserId());
        systemSettingService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @PutMapping("edit")
    public ResultBase editSystemSetting(@RequestBody SystemSetting bean) {
        if (!Tools.checkUuid(bean.getSystemSetId()))
            throw new CustomException(CommonErrorEnums.UUID_ERROR);
            
        systemSettingService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @DeleteMapping("delete")
    public ResultBase delete(@Pattern(regexp = Constant.uuidRegex,message = "id不规范") String id) {
        systemSettingService.deleteById(id);
        return ResultBase.succ();
    }

    @PostMapping("deleteBatch")
    public ResultBase deleteBatch(@RequestBody List<String> ids) {
        systemSettingService.deleteByIds(ids);
        return ResultBase.succ();
    }

}

