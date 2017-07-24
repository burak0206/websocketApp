package com.chat.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by burakdagli on 27.06.2017.
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public String loginRedirect(){ return "login";}

    @RequestMapping("/login")
    public String loginPage(){ return "login";}

    @RequestMapping("/chat")
    public String chat() {
        return "chat";
    }

}
