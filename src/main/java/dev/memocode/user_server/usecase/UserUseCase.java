package dev.memocode.user_server.usecase;

import dev.memocode.user_server.domain.user.dto.UserCreateDTO;
import dev.memocode.user_server.domain.user.dto.UserInfo;
import dev.memocode.user_server.domain.user.validation.ValidAccountId;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface UserUseCase {

    UUID createUser(@Valid UserCreateDTO dto);

    UserInfo userInfo(@ValidAccountId UUID accountId);

    List<UserInfo> findAll();
}
