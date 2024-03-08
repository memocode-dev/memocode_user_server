package dev.memocode.user_server.usecase.impl;

import dev.memocode.user_server.domain.user.dto.UserCreateDTO;
import dev.memocode.user_server.domain.user.dto.UserInfo;
import dev.memocode.user_server.domain.user.entity.User;
import dev.memocode.user_server.domain.user.mapper.UserInfoMapper;
import dev.memocode.user_server.domain.user.service.UserService;
import dev.memocode.user_server.event.UserCreatedEvent;
import dev.memocode.user_server.usecase.UserUseCase;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.UUID;

@Component
@Validated
@Transactional
@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase {

    private final UserService userService;

    private final UserInfoMapper userInfoMapper;

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public UUID createUser(@Valid UserCreateDTO dto) {

        User user = userService.createUser(dto);

        // USER_CREATED 이벤트 발생
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .userInfo(userInfoMapper.fromUser(user))
                .build();
        eventPublisher.publishEvent(userCreatedEvent);

        return user.getId();
    }

    @Override
    public UserInfo userInfo(@NotNull(message = "ACCOUNT_ID_NOT_NULL:accountId must not be null") UUID accountId) {

        User user = userService.findByAccountIdElseThrow(accountId);

        return userInfoMapper.fromUser(user);
    }

    @Override
    public List<UserInfo> findAll() {
        List<User> users = userService.findAll();

        return userInfoMapper.fromUsers(users);
    }
}
