package com.chat.demo.controller;

import com.chat.demo.dao.UserDao;
import com.chat.demo.domain.User;
import com.chat.demo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by burakdagli on 29.06.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRestApiControllerTest {

    private MockHttpServletResponse response = new MockHttpServletResponse();
    private MockHttpServletRequest request = new MockHttpServletRequest();

    @InjectMocks
    private UserRestApiController userRestApiController;

    @Mock
    private UserService userService;
    @Mock
    private UserDao userDao;


    @Test
    public void shouldReturnUsernameNotNull(){
        User user = new User("username");
        when(userService.findByUsername(user.getName())).thenReturn(user);
        User returnedUser = userRestApiController.user(null);
        assertNotNull("Username should not be null", returnedUser.getName());
    }

    @Test
    public void shouldReturnUsernameNotEmpty(){
        User user = new User("username");
        when(userService.findByUsername(user.getName())).thenReturn(user);
        User returnedUser = userRestApiController.user("");
        assertNotNull("Username should not be null", returnedUser.getName());
    }


    @Test
    public void shouldInvokeUserService(){
        User user = new User("username");
        when(userService.findByUsername(user.getName())).thenReturn(user);
        User returnedUser = userRestApiController.user("");
        verify(userService).findByUsername(user.getName());
        assertNotNull(returnedUser);
    }


}