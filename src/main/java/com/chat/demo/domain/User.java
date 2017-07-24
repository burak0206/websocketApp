package com.chat.demo.domain;

import java.util.UUID;

/**
 * Created by burakdagli on 29.06.2017.
 */
public class User {
    private String id;
    private String name;

    public User(){
        this.id = UUID.randomUUID().toString();
    }

    public User(String username){
        this.name = username;
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
