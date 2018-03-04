package com.example.peter.chattest;

import java.io.Serializable;

/**
 * Created by Peter on 04/03/2018.
 */

public class User implements Serializable{

    private int u_id;
    private String name;
    private String email;

    public User(int u_id, String name, String email) {
        this.u_id = u_id;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return u_id;
    }

    public void setId(int u_id) {
        this.u_id = u_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
