package com.huasheng.wmssystem.controller.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huasheng.wmssystem.core.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Hidden;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ：xjTang
 * @Date ：Created By 2021/5/14 17:30
 * @Description ：
 */

@Controller
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;


    @GetMapping("getById")
    @ResponseBody
    @ApiOperation(value = "根据Id查询角色")
    @RequiresAuthentication
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


    @GetMapping("userlist")
    @ApiOperation(value = "分页查询")
//    @ApiImplicitParam(name = "id", value = "商品ID",  paramType = "path", required = true, dataType =  "Integer")
    public Map list( String nameParam,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        Map map = new HashMap();
        try {
            ObjectMapper objectMapper=new ObjectMapper();

            String json=objectMapper.writeValueAsString(userService.findList(nameParam,page-1,pageSize));
            System.out.println(json);
            map.put("data",json);
            map.put("nameParam",nameParam);
            map.put("code",1);


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }catch (Exception e){
            map.put("code",0);
            map.put("msg","失败");
            e.printStackTrace();
        }


        return map;
    }

}
