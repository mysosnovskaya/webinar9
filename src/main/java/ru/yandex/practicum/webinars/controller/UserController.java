package ru.yandex.practicum.webinars.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.webinars.model.User;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Integer, User> userById = new HashMap<>();
    private int idGenerator = 1;

    @PostMapping()
    public User register(@RequestBody @Valid User user) {
        if (userById.values().stream().noneMatch(u -> u.getLogin().equals(user.getLogin()))) {
            user.setId(idGenerator++);
            userById.put(user.getId(),user);
            log.error("Пользователь с логином {} добавлен", user.getLogin());
            return user;
        } else {
            log.error("Пользователь с логином {} уже существует", user.getLogin());
            throw new RuntimeException("Пользователь с таким логином уже существует!");
        }
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return new ArrayList<>(userById.values());
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
