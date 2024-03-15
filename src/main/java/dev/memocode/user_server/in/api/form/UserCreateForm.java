package dev.memocode.user_server.in.api.form;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserCreateForm {

    @NotNull(message = "USERNAME_NOT_NULL:Username must not be null")
    @Size(min = 4, max = 20, message = "USERNAME_SIZE:Username must be between 4 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "USERNAME_ONLY_ALPHANUMERIC:Username must contain only letters and numbers")
    private String username;

    @NotNull(message = "NICKNAME_NOT_NULL:Nickname must not be null")
    @Size(min = 4, max = 20, message = "NICKNAME_SIZE:Nickname must be between 4 and 20 characters")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]+$", message = "NICKNAME_INVALID_CHARACTERS:Nickname must contain only letters, numbers, and Korean characters")
    private String nickname;
}
