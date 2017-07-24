package com.chat.demo.service;

import com.chat.demo.dao.UserDao;
import com.chat.demo.domain.User;
import com.chat.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Created by burakdagli on 29.06.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserDao userDao;

    @Test
    public void shouldEqualUserWithReturnDao(){
        String username ="username";
        User user = new User(username);
        when(userDao.findByUsername(username)).thenReturn(user);
        User returnedUser = userService.findByUsername(username);
        assertTrue("Userid should be equal", returnedUser.getId().equals(user.getId()));
        assertTrue("Username should be equal", returnedUser.getName().equals(user.getName()));
    }


    @Test
    public void shouldReturnNull(){
        String username ="username";
        when(userDao.findByUsername(username)).thenReturn(null);
        User returnedUser = userService.findByUsername(username);
        assertNull("User should be null", returnedUser);
    }

}
