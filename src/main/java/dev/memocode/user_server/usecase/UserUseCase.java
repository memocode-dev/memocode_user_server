package dev.memocode.user_server.usecase;

import dev.memocode.user_server.domain.user.dto.UserCreateDTO;
import dev.memocode.user_server.domain.user.dto.UserInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public interface UserUseCase {

    UUID createUser(@Valid UserCreateDTO dto);

    UserInfo userInfo(@NotNull(message = "ACCOUNT_ID_NOT_NULL:accountId must not be null") UUID accountId);

    List<UserInfo> findAll();
}
