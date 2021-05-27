package com.huasheng.wmssystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huasheng.wmssystem.core.service.TestService;
import com.huasheng.wmssystem.domain.entity.Test;
import com.huasheng.wmssystem.domain.model.UserRedis;
import com.huasheng.wmssystem.domain.model.resultmodel.ResultBase;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/test")
@Slf4j
public class HelloController {

    @Autowired
    TestService testService;

    /**
     * @Description hello测试
     * @Param       []
     * @return      java.lang.String
     * @Author      xjTang
     * @Date        Create by 2021/5/14 15:26
     */
    @GetMapping("/hello")
    public String hello() {
//        log.info("helloword111");
//
//        log.warn("helloword222");
        return "welcome to wms!";


    }

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @GetMapping("/redistest")
    public String test() {

        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();


        ops1.set("message", "hello xiaoxia!");

        String message = ops1.get("message");


        ValueOperations ops2 = redisTemplate.opsForValue();
        UserRedis user1 = new UserRedis();
        user1.setId(1);
        user1.setName("xiaoxia");
        user1.setAge(28);
        ops2.set("user", user1);


        UserRedis user2 = (UserRedis) ops2.get("user");

        HashOperations<String, String, String> ops3 = redisTemplate.opsForHash();


        return message + "___" + user2.toString();
    }



    @GetMapping("rolelist")
    @ApiOperation(value = "分页查询")
//    @ApiImplicitParam(name = "id", value = "商品ID",  paramType = "path", required = true, dataType =  "Integer")
    public String list(HashMap map, String nameParam,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        try {
            ObjectMapper objectMapper=new ObjectMapper();

            String json=objectMapper.writeValueAsString(testService.findList(nameParam,page-1,pageSize));
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


        return "role_list";
    }

    @PostMapping("edit")
    @ResponseBody
    @ApiOperation(value = "编辑角色")
//    @ApiImplicitParam(name = "Content-Type", defaultValue = "application/json", required = true ,value = "header param")
//    @ApiImplicatParams({
//            @ApiImplicatParam(paramType = "header", name = "Content-Type", defaultValue = "application/json", required = true ,value = "header param")
//    })
//    @RequiresPermissions("notice:edit")//权限管理;
//    (@Parameter(in = ParameterIn.DEFAULT, description = "Pet object that needs to be added to the store", required=true, schema=@Schema()) @Valid
//    @org.springframework.web.bind.annotation.RequestBody
//    Pet body)
    public Map editMenu(
                        @RequestBody
                                Test bean) {
        Map map = new HashMap();
        try {
            return testService.edit(bean);
        } catch (Exception e) {
            map.put("code", 0);
            map.put("msg", "失败");
            e.printStackTrace();
            return map;
        }
    }

    @GetMapping("getById")
    @ApiOperation(value = "根据Id查询角色")
//    @RequiresAuthentication
    public ResultBase toEdit(String id) {
        Map map = new HashMap();
        ResultBase resultBase =new ResultBase();
        try {
            return ResultBase.succ(testService.findByRid(id));
//            map.put("bean", testService.findByRid(id));
//            map.put("code", 1);
//            map.put("msg", "成功");
        } catch (Exception e) {

            return ResultBase.fail("10020","失败");
//            map.put("code", 0);
//            map.put("msg", "失败");
//            e.printStackTrace();
        }
//        return map;
    }

}
