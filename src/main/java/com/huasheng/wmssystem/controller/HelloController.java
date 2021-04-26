package com.huasheng.wmssystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huasheng.wmssystem.domain.entity.User;
import com.huasheng.wmssystem.core.service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("test")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "welcome to wms!";
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/redistest")
    public String test() {

        ValueOperations<String, String> ops1 = stringRedisTemplate.opsForValue();


        ops1.set("message", "hello xiaoxia!");

        String message = ops1.get("message");


        ValueOperations ops2 = redisTemplate.opsForValue();
        User user1 = new User();
        user1.setId(1);
        user1.setName("xiaoxia");
        user1.setAge(28);
        ops2.set("user", user1);


        User user2 = (User) ops2.get("user");

        HashOperations<String, String, String> ops3 = redisTemplate.opsForHash();


        return message + "___" + user2.toString();
    }


    @Autowired
    RolesService roleService;

    @RequestMapping("rolelist")
    public String list(HashMap map, String nameParam,
                       @RequestParam(value = "page", defaultValue = "1") Integer page,
                       @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {

        try {
            ObjectMapper objectMapper=new ObjectMapper();

            String json=objectMapper.writeValueAsString(roleService.findList(nameParam,page-1,pageSize));
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


}
