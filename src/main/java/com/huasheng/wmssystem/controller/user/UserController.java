package com.huasheng.wmssystem.controller.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huasheng.wmssystem.core.service.UserService;
import com.huasheng.wmssystem.domain.entity.User;
import com.huasheng.wmssystem.domain.model.resultmodel.DataResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ListResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/14 17:30
 * @Description ：
 */
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("getById")
    @ApiOperation(value = "根据Id查询角色")
    public DataResult<User> toEdit(String id) {
        User user = userService.findByUserId(id);

        String name = user.getRealName();
        Assert.notNull(user, "用户不存在");

        DataResult<User> dataResult=new DataResult<>();
        return dataResult.succ(user);
    }


    @GetMapping("userlist")
    @ApiOperation(value = "分页查询")
    public ListResult<List<User>> list(String nameParam,
                                 @RequestParam(value = "page", defaultValue = "1") Integer page,
                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        Page<User> users = userService.findList(nameParam, page - 1, pageSize);

        ListResult<User> listResult=new ListResult<>();
        return listResult.succ(users);

    }

}
