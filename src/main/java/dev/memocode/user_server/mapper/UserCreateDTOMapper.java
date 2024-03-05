package dev.memocode.user_server.mapper;

import dev.memocode.user_server.dto.UserCreateDTO;
import dev.memocode.user_server.dto.form.UserCreateForm;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserCreateDTOMapper {

    public UserCreateDTO fromUserCreateFormAndAccountId(UserCreateForm form, UUID accountId) {
        return UserCreateDTO.builder()
                .username(form.getUsername())
                .nickname(form.getNickname())
                .accountId(accountId)
                .build();
    }
}
