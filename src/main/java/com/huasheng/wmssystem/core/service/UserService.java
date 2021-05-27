package com.huasheng.wmssystem.core.service;

import com.huasheng.wmssystem.data.dao.UserRepository;
import com.huasheng.wmssystem.data.dao.testRepository;
import com.huasheng.wmssystem.domain.entity.Test;
import com.huasheng.wmssystem.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService{

    @Autowired
    UserRepository userRepository;


    public User findByUserId(String userId){
        User user = userRepository.findByUserId(userId);

        return user;
    }

    public User findByUserName(String username){
        User user = userRepository.findByUsername(username);

        return user;
    }


}
