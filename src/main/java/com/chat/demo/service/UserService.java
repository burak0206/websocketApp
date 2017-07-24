package com.chat.demo.service;

import com.chat.demo.dao.UserDao;
import com.chat.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by burakdagli on 29.06.2017.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public User findByUsername(String username){
        return userDao.findByUsername(username);
    }
}
