package com.huasheng.wmssystem.controller.user;

import com.huasheng.wmssystem.core.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/14 17:30
 * @Description ：
 */

@RestController
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("getById")
    @ResponseBody
    @ApiOperation(value = "根据Id查询角色")
    public Map toEdit(String id) {
        Map map = new HashMap();
        try {
            map.put("bean", userService.findByUserId(id));
            map.put("code", 1);
            map.put("msg", "成功");
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", "失败");
            e.printStackTrace();
        }
        return map;
    }


}
