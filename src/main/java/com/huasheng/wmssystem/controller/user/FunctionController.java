package com.huasheng.wmssystem.controller.user;

import com.huasheng.wmssystem.core.service.FunctionService;
import com.huasheng.wmssystem.domain.entity.Function;
import com.huasheng.wmssystem.domain.model.paramodel.web.FunctionListPara;
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
@RequestMapping("/api/function")
@Tag(name = "FunctionController", description = "功能设置表接口")
public class FunctionController {

    @Autowired
    FunctionService functionService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("getById/{id}")
    public DataResult<Function> getById(@PathVariable @Pattern(regexp = Constant.uuidRegex, message = "id不规范") String id) {
        Function function = functionService.findByFunctionId(id);

        DataResult<Function> dataResult = new DataResult<>();
        return dataResult.succ(function);
    }

    @PostMapping("getList")
    public ListResult<List<Function>> list(FunctionListPara listPara) {
        return functionService.findList(listPara);
    }

    @PostMapping("add")
    public ResultBase addFunction(@RequestBody Function bean) {
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        bean.setAddUser(accountProfile.getUserId());
        functionService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @PutMapping("edit")
    public ResultBase editFunction(@RequestBody Function bean) {
        if (!Tools.checkUuid(bean.getFunctionId()))
            throw new CustomException(CommonErrorEnums.UUID_ERROR);

        functionService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @DeleteMapping("delete")
    public ResultBase delete(@Pattern(regexp = Constant.uuidRegex, message = "id不规范") String id) {
        functionService.deleteById(id);
        return ResultBase.succ();
    }

    @PostMapping("deleteBatch")
    public ResultBase deleteBatch(@RequestBody List<String> ids) {
        functionService.deleteByIds(ids);
        return ResultBase.succ();
    }

}

