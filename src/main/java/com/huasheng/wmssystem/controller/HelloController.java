package com.huasheng.wmssystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huasheng.wmssystem.core.service.TestService;
import com.huasheng.wmssystem.domain.entity.Test;
import com.huasheng.wmssystem.domain.model.redismodel.UserRedis;
import com.huasheng.wmssystem.domain.model.resultmodel.DataResult;
import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
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
     * @return java.lang.String
     * @Description hello测试
     * @Param []
     * @Author xjTang
     * @Date Create by 2021/5/14 15:26
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
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "page",defaultValue = "1",example = "1"),
//            @ApiImplicitParam(name = "pageSize",defaultValue = "10")
//    })

//    public String list(
//            @RequestParam(value = "page", defaultValue = "1") int page,
//            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

//        public String list(
//            @ApiParam(value = "page", example = "1",defaultValue = "1") @PathVariable Integer page,
//            @ApiParam(value = "pageSize", example = "10") Integer pageSize) {

//    public String list(Integer page,int pageSize) {
    public String list(ListPara listPara) {

        try {
            String nameParam = "";
            ObjectMapper objectMapper = new ObjectMapper();

//            String json = objectMapper.writeValueAsString(testService.findList(nameParam, page - 1, pageSize-1));
            String json = objectMapper.writeValueAsString(testService.findList(nameParam, listPara.getPage() - 1, listPara.getPageSize() - 1));
            System.out.println(json);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (Exception e) {

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

    @DeleteMapping("delete")
    @ResponseBody
    @ApiOperation(value = "删除角色")
//    @ApiImplicitParam(name = "Content-Type", defaultValue = "application/json", required = true ,value = "header param")
//    @ApiImplicatParams({
//            @ApiImplicatParam(paramType = "header", name = "Content-Type", defaultValue = "application/json", required = true ,value = "header param")
//    })
//    @RequiresPermissions("notice:edit")//权限管理;
//    (@Parameter(in = ParameterIn.DEFAULT, description = "Pet object that needs to be added to the store", required=true, schema=@Schema()) @Valid
//    @org.springframework.web.bind.annotation.RequestBody
//    Pet body)
    public Map delete(String id) {
        Map map = new HashMap();
        try {
            testService.deleteById(id);
            return map;
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
    public DataResult<Test> toEdit(String id) {

        DataResult<Test> dataResult=new DataResult<>();
        return dataResult.succ(testService.findByRid(id));

    }

}
