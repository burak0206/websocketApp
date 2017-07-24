package com.chat.demo.controller;

import com.chat.demo.dao.ParticipantRepository;
import com.chat.demo.domain.User;
import com.chat.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by burakdagli on 29.06.2017.
 */
@RestController
public class UserRestApiController {

    @Autowired private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private ParticipantRepository participantRepository;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public User user(@RequestParam("username") String username){
        if (username == null || username.isEmpty()){
            username = "username";
        }
        User user = userService.findByUsername(username);
        //participantRepository.add(user.getId(),user);
        return user;
    }
}
