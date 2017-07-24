package com.chat.demo.controller;

import com.chat.demo.dao.ParticipantRepository;
import com.chat.demo.domain.ChatMessage;
import com.chat.demo.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by burakdagli on 27.06.2017.
 */

@Controller
public class ChatMessageController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ParticipantRepository participantRepository;


    @SubscribeMapping("/participants")
    public List<User> retrieveParticipants() {
         List<User> users = new ArrayList<User>();
         for (User user: participantRepository.getActiveSessions().values()){
             users.add(user);
         }
         return users;
    }

    @MessageMapping("/add/participants")
    @SendTo("/topic/add/participants")
    public User addParticipants(User user) {
        participantRepository.add(user.getId(),user);
        return user;
    }

    @MessageMapping("/logout")
    @SendTo("/topic/remove/participants")
    public User removeParticipants(User user) {
        participantRepository.removeParticipant(user.getId());
        return user;
    }


    @MessageMapping("/hello")
    @SendTo("/topic/newMessage")
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessage;
    }


}
