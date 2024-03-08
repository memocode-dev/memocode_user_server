package dev.memocode.user_server.domain.user.dto;

import dev.memocode.user_server.domain.user.validation.ValidNickname;
import dev.memocode.user_server.domain.user.validation.ValidUserId;
import dev.memocode.user_server.domain.user.validation.ValidUsername;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserInfo {
    @ValidUserId
    private UUID id;
    @ValidUsername
    private String username;
    @ValidNickname
    private String nickname;
}
