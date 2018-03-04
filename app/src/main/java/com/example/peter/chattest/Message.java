package com.example.peter.chattest;

import java.util.Date;

/**
 * Created by Peter on 04/03/2018.
 */

public class Message {

    private String msg;
    private String user_name;
    private long messageTime;

    public Message (String msg,String user_name){
        this.msg=msg;
        this.user_name=user_name;

        setMessageTime(new Date().getTime());

    }

    public Message (){

    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser_id() {
        return user_name;
    }

    public void setUser_id(String user_name) {
        this.user_name = user_name;
    }

    public long getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }
}
