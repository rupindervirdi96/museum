package com.example.museum;

import java.io.Serializable;

public class user implements Serializable {
    private String name;
    private String userId;
    private String password;
    private String email;
    private String age;


    public user(String name, String userId, String password, String email, String age) {
        this.name = name;
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public user(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
