package com.ccm.user.user.domain;

import java.util.List;

public interface UserRepository {

    public User find(UserId userId);

    public boolean exists(UserId userId);

    public List<User> search(UserName userName);

    public void create(User user);

    public User update(User user);

    public void delete(User user);
}
