package dev.memocode.user_server.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserCreateDTO {

    @NotBlank
    private String username;

    @NotBlank
    private String nickname;

    @NotNull
    private UUID accountId;
}
