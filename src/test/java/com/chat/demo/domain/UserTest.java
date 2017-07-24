package com.chat.demo.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by burakdagli on 29.06.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserTest {

    @Test
    public void shouldDifferentUserID(){
        User user1 = new User("username");
        User user2 = new User("username");
        assertFalse("Userid should be unique", user1.getId().equals(user2.getId()));
    }

    @Test
    public void shouldUserIDNotNull(){
        User user = new User();
        assertNotNull("Userid should not be null", user.getId());
    }

    @Test
    public void shouldUserNameNull(){
        User user = new User();
        assertNull("Username can be null", user.getName());
    }

}