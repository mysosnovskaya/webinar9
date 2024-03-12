package ru.yandex.practicum.filmorate;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    private final Map<Integer, User> users = new HashMap<>();

    private int idGenerator = 1;

    @PostMapping
    public User createUser(@RequestBody @Validated(UpdateGroup.class) User user) {
//        validateUser(user);
        user.setId(idGenerator++);
        users.put(user.getId(), user);
        return user;
    }

    @GetMapping
    public List<User> getAll() {
     return new ArrayList<>(users.values());
    }

//    private void validateUser(User user) {
//        if (user.getName() == null || user.getName().isBlank()) {
//            throw new RuntimeException("Имя пользователя не должно быть пустым");
//        }
//    }
}
