package ru.yandex.practicum.filmorate;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Data
public class User {
    @Null(groups = {CreateGroup.class})
    @NotNull(groups = {UpdateGroup.class})
    private Integer id;
    @NotBlank
    @StartsWith
    private String name;
    @NotNull
    private String email;
}
