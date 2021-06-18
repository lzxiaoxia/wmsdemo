package com.huasheng.wmssystem.controller.user;

import com.huasheng.wmssystem.core.service.InformationService;
import com.huasheng.wmssystem.domain.entity.Information;
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
@RequestMapping("/api/information")
@Tag(name = "InformationController", description = "消息通知表接口")
public class InformationController {

    @Autowired
    InformationService informationService;

    @Autowired
    JwtUtils jwtUtils;

    @GetMapping("getById/{id}")
    public DataResult<Information> getById(@PathVariable @Pattern(regexp = Constant.uuidRegex,message = "id不规范") String id) {
        Information information = informationService.findByInformationId(id);

        DataResult<Information> dataResult = new DataResult<>();
        return dataResult.succ(information);
    }

    @PostMapping("getList")
    public ListResult<List<Information>> list(ListPara listPara) {
        return informationService.findList(listPara);
    }

    @PostMapping("add")
    public ResultBase addInformation(@RequestBody Information bean) {
        AccountProfile accountProfile = (AccountProfile) SecurityUtils.getSubject().getPrincipal();
        bean.setAddUser(accountProfile.getUserId());
        informationService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @PutMapping("edit")
    public ResultBase editInformation(@RequestBody Information bean) {
        if (!Tools.checkUuid(bean.getInfoId()))
            throw new CustomException(CommonErrorEnums.UUID_ERROR);
            
        informationService.addOrEdit(bean);
        return ResultBase.succ();
    }

    @DeleteMapping("delete")
    public ResultBase delete(@Pattern(regexp = Constant.uuidRegex,message = "id不规范") String id) {
        informationService.deleteById(id);
        return ResultBase.succ();
    }

    @PostMapping("deleteBatch")
    public ResultBase deleteBatch(@RequestBody List<String> ids) {
        informationService.deleteByIds(ids);
        return ResultBase.succ();
    }

}

