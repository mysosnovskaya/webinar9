package ru.yandex.practicum.webinars.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.webinars.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Integer, User> userById = new HashMap<>(Map.of(1, new User(1,"admin","admin@yandex.ru","password")));
    private int idGenerator = 2;

    @PostMapping()
    public User register(@RequestBody User user) {
        user.setId(idGenerator++);
        userById.put(user.getId(),user);
        return user;
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userById.values().stream().filter(user -> user.getId() != 1).collect(Collectors.toList());
    }

    @PutMapping
    public User updateUser(@RequestBody User user) {
        if(userById.containsKey(user.getId())) {
            userById.put(user.getId(), user);
            return user;
        } else {
            log.error("Пользователь с id = {} не найден", user.getId());
            throw new RuntimeException("Пользователь с таким id не существует");
        }
    }
}
