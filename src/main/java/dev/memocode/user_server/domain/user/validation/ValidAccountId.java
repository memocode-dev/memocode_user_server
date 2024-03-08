package dev.memocode.user_server.domain.user.validation;

import jakarta.validation.Constraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import org.hibernate.validator.constraints.UUID;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = {})
@Target({ METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
@NotNull(message = "ACCOUNT_ID_NOT_NULL:accountId must not be null")
@UUID(message = "ACCOUNT_ID_NOT_UUID:accountId must be uuid")
public @interface ValidAccountId {
}
