package com.chat.demo.smoketest;

import com.chat.demo.configuration.App;
import com.chat.demo.controller.PageController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

/**
 * Created by burakdagli on 29.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes={App.class})
public class ApplicationTest {

    @Autowired
    private PageController pageController;

    @Test
    public void contextLoads() throws Exception {
        assertNotNull(pageController);
    }

}
