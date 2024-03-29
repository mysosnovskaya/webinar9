package ru.yandex.practicum.filmorate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD })
@Retention(RUNTIME)
@Constraint(validatedBy = {StartsWithValidator.class})
public @interface StartsWith {

    String message() default "Поле должно начинаться с java";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
