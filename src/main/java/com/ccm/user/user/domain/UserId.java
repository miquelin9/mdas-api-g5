package com.ccm.user.user.domain;

public class UserId {

    public UserId(int userId) {
        this.userId = userId;
    }

    private int userId;

    @Override
    public String toString() {
        return String.valueOf(userId);
    }
}
