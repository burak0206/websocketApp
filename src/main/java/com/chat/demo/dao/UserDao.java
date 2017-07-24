package com.chat.demo.dao;

import com.chat.demo.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by burakdagli on 29.06.2017.
 */
@Repository
public class UserDao {
    public User findByUsername(String username){
        return new User(username);
    }
}
