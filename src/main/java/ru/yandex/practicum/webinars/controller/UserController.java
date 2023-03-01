package ru.yandex.practicum.webinars.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.webinars.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    private final List<User> users = new ArrayList<>(List.of((
            new User(1,"admin","admin@yandex.ru","password"))));
    private int idGenerator = 2;

    @PostMapping()
    public User register(@RequestBody User user) {
        user.setId(idGenerator++);
        users.add(user);
        return user;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return users.stream().filter(user -> user.getId() != 1).collect(Collectors.toList());
    }
}
