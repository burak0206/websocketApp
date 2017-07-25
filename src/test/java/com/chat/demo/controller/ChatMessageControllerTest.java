package com.chat.demo.controller;

import com.chat.demo.dao.ParticipantRepository;
import com.chat.demo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by burakdagli on 25.07.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ChatMessageControllerTest {

    private MockHttpServletResponse response = new MockHttpServletResponse();
    private MockHttpServletRequest request = new MockHttpServletRequest();

    @InjectMocks
    private ChatMessageController chatMessageController;

    @Mock
    private ParticipantRepository participantRepository;

    @Test
    public void shouldReturnNotEmptyParticipants(){
        User user1 = new User("username");
        User user2 = new User("username");
        when(participantRepository.getActiveSessions()).thenReturn(new ConcurrentHashMap<String, User>() {{
            put(user1.getId(), user1);
            put(user2.getId(), user2);
        }});
        List<User> returnedUser = chatMessageController.retrieveParticipants();
        assertEquals("ParticipantList should not be Empty",2, returnedUser.size());
    }

    @Test
    public void shouldReturnEmptyParticipants(){

        when(participantRepository.getActiveSessions()).thenReturn(new ConcurrentHashMap<String, User>() {{}});
        List<User> returnedUser = chatMessageController.retrieveParticipants();
        assertEquals("ParticipantList should be Empty",0, returnedUser.size());
    }





}