package dev.memocode.user_server.domain.user.dto;

import dev.memocode.user_server.domain.user.validation.ValidAccountId;
import dev.memocode.user_server.domain.user.validation.ValidNickname;
import dev.memocode.user_server.domain.user.validation.ValidUsername;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateDTO {

    @ValidUsername
    private String username;

    @ValidNickname
    private String nickname;

    @ValidAccountId
    private UUID accountId;
}
