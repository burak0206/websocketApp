package com.chat.demo.domain;

import java.util.Date;

/**
 * Created by burakdagli on 27.06.2017.
 */
public class ChatMessage {
    private String text;
    private String author;
    private String id;
    private Date createDate;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}