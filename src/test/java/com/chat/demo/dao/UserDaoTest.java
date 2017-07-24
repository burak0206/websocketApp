package com.chat.demo.dao;

import com.chat.demo.domain.User;
import com.chat.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by burakdagli on 29.06.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserDaoTest {

    @InjectMocks
    UserDao userDao;

    @Test
    public void shouldUserNotNull(){
        String username ="username";
        User user = new User(username);
        User returnedUser = userDao.findByUsername(username);
        assertNotNull("User should not be null",returnedUser);
    }

    @Test
    public void shouldUserIdNotNull(){
        String username ="username";
        User user = new User(username);
        User returnedUser = userDao.findByUsername(username);
        assertNotNull("Userid should be null",returnedUser.getId());
    }

}