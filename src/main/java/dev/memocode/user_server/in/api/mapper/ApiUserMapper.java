package dev.memocode.user_server.in.api.mapper;

import dev.memocode.user_server.domain.user.dto.UserCreateDTO;
import dev.memocode.user_server.in.api.form.UserCreateForm;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ApiUserMapper {
    public UserCreateDTO userCreateFormAndUserId_to_userCreateDTO(UserCreateForm form, UUID userId) {
        return UserCreateDTO.builder()
                .username(form.getUsername())
                .nickname(form.getNickname())
                .userId(userId)
                .build();
    }
}
