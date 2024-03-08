package dev.memocode.user_server.domain.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {})
@Target({ FIELD })
@Retention(RUNTIME)
@NotNull(message = "USERNAME_NOT_NULL:Username must not be null")
@Size(min = 4, max = 20, message = "USERNAME_SIZE:Username must be between 4 and 20 characters")
@Pattern(regexp = "^[a-zA-Z0-9]+$", message = "USERNAME_ONLY_ALPHANUMERIC:Username must contain only letters and numbers")
public @interface ValidUsername {
}
