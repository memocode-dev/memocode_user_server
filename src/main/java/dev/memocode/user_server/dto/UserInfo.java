package dev.memocode.user_server.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserInfo {
    private UUID id;
    private String username;
    private String nickname;
}
