package dev.memocode.user_server.usecase;

import dev.memocode.user_server.domain.user.dto.UserCreateDTO;
import dev.memocode.user_server.domain.user.dto.UserInfo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Validated
public interface UserUseCase {

    UUID createUser(@Valid UserCreateDTO dto);

    UserInfo userInfo(@NotNull(message = "USER_ID_NOT_NULL:userId must not be null") UUID userId);

    List<UserInfo> findAll();
}
