package com.ccm.user.user.domain;

import java.util.Objects;

public class UserId {

    public UserId(int userId) {
        this.userId = userId;
    }

    private int userId;

    @Override
    public String toString() {
        return String.valueOf(userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserId userId1 = (UserId) o;
        return userId == userId1.userId;
    }
}
