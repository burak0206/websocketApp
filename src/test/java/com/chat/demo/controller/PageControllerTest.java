package com.chat.demo.controller;

import com.chat.demo.configuration.App;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by burakdagli on 29.06.2017.
 */

@RunWith(SpringRunner.class)
@WebMvcTest(PageController.class)
@ContextConfiguration(classes={App.class})
public class PageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void greetingShouldReturnHomePage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("LoginPage")));
    }

    @Test
    public void greetingShouldReturnLoginPage() throws Exception {
        this.mockMvc.perform(get("/login")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("LoginPage")));
    }

    @Test
    public void greetingShouldReturnChatPage() throws Exception {
        this.mockMvc.perform(get("/chat")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("ChatPage")));
    }

}