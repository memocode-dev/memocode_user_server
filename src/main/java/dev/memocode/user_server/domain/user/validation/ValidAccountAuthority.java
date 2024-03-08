package dev.memocode.user_server.domain.user.validation;

import jakarta.validation.constraints.NotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({ FIELD })
@Retention(RUNTIME)
@NotNull(message = "ACCOUNT_AUTHORITY_NOT_NULL:Nickname must not be null")
public @interface ValidAccountAuthority {
}
