package dev.memocode.user_server.domain.user.validation;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD })
@Retention(RUNTIME)
@NotNull(message = "NICKNAME_NOT_NULL:Nickname must not be null")
@Size(min = 4, max = 20, message = "NICKNAME_SIZE:Nickname must be between 4 and 20 characters")
@Pattern(regexp = "^[a-zA-Z0-9가-힣]+$", message = "NICKNAME_INVALID_CHARACTERS:Nickname must contain only letters, numbers, and Korean characters")
public @interface ValidNickname {
}
