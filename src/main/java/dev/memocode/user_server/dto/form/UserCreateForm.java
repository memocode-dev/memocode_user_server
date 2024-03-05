package dev.memocode.user_server.dto.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateForm {

    @NotBlank
    private String username;

    @NotBlank
    private String nickname;
}
