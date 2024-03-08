package dev.memocode.user_server.usecase;

import dev.memocode.user_server.domain.user.dto.UserCreateDTO;
import dev.memocode.user_server.domain.user.dto.UserInfo;

import java.util.List;
import java.util.UUID;

public interface UserUseCase {

    UUID createUser(UserCreateDTO dto);

    UserInfo userInfo(UUID accountId);

    List<UserInfo> findAll();
}
