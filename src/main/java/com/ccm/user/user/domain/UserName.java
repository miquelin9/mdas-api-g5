package com.ccm.user.user.domain;

public class UserName {

    public UserName(String name) {
        this.name = name;
    }

    public boolean contains(UserName userName){
        return userName.getName().contains(this.name);
    }

    public String getName() {
        return name;
    }

    private String name;
}
