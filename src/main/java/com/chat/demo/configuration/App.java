package com.chat.demo.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by burakdagli on 27.06.2017.
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.chat.demo")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
