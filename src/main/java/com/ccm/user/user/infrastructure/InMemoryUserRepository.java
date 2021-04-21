package com.ccm.user.user.infrastructure;

import com.ccm.user.user.domain.User;
import com.ccm.user.user.domain.UserId;
import com.ccm.user.user.domain.UserName;
import com.ccm.user.user.domain.UserRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.awt.event.ItemEvent;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.logging.XMLFormatter;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ApplicationScoped
@Named("InMemory")
public class InMemoryUserRepository implements UserRepository {

    List<User> inMemoryUsers = new ArrayList<>();

    @Override
    public User find(UserId userId) {
        return inMemoryUsers.stream()
                .filter(x -> x.getUserId().equals(userId)).findAny()
                .orElse(null);
    }

    @Override
    public boolean exists(UserId userId) {
        return inMemoryUsers.stream()
                .anyMatch(x -> x.getUserId().equals(userId));
    }

    @Override
    public List<User> search(UserName userName) {
        return inMemoryUsers.stream()
                .filter(x -> x.getName().contains(userName))
                .collect(Collectors.toList());
    }

    @Override
    public User update(User user) {
        OptionalInt index = IntStream.range(0, inMemoryUsers.size())
                .filter(i -> user.getUserId().equals(inMemoryUsers.get(i).getUserId()))
                .findFirst();

        inMemoryUsers.set(index.getAsInt(), user);
        return user;
    }

    @Override
    public void create(User user) {
        inMemoryUsers.add(user);
    }

    @Override
    public void delete(User user) {
        inMemoryUsers.remove(user);
    }
}