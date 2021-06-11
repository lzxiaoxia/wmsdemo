package com.huasheng.wmssystem.controller;

import com.huasheng.wmssystem.domain.model.paramodel.ListPara;
import com.huasheng.wmssystem.domain.model.redismodel.UserRedis;
import com.huasheng.wmssystem.domain.model.resultmodel.DataResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ListResult;
import com.huasheng.wmssystem.domain.model.resultmodel.ResultBase;
import com.huasheng.wmssystem.utils.JwtUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/test")
@Slf4j
@Tag(name = "HelloController", description = "测试接口")
public class HelloController {

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


}
