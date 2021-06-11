package com.huasheng.wmssystem.controller.user;

import com.huasheng.wmssystem.core.service.UserService;
import com.huasheng.wmssystem.domain.entity.User;
import com.huasheng.wmssystem.domain.model.resultmodel.DataResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/14 17:30
 * @Description ：
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("getById")
//    @ApiOperation(value = "根据Id查询角色")
    public DataResult<User> getById(String id) {
        User user = userService.findByUserId(id);

        DataResult<User> dataResult = new DataResult<>();
        return dataResult.succ(user);
    }

    @GetMapping("getList")
//    @ApiOperation(value = "分页查询")
    public ListResult<List<User>> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        Page<User> users = userService.findList("", page - 1, pageSize);

        ListResult<User> listResult = new ListResult<>();
        return listResult.succ(users);
    }


}
