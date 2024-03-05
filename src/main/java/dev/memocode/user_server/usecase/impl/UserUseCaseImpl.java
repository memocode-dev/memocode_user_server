package dev.memocode.user_server.usecase.impl;

import dev.memocode.user_server.domain.external.account.service.AccountService;
import dev.memocode.user_server.domain.user.entity.User;
import dev.memocode.user_server.domain.user.service.UserService;
import dev.memocode.user_server.dto.UserCreateDTO;
import dev.memocode.user_server.dto.UserInfo;
import dev.memocode.user_server.mapper.UserInfoMapper;
import dev.memocode.user_server.usecase.UserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class UserUseCaseImpl implements UserUseCase {

    private final UserService userService;

    private final AccountService accountService;

    private final UserInfoMapper userInfoMapper;

    @Override
    public UUID createUser(@Valid UserCreateDTO dto) {

        User user = userService.createUser(dto);

        accountService.addAuthority("USER", dto.getAccountId());

        return user.getId();
    }

    @Override
    public UserInfo userInfo(UUID accountId) {

        User user = userService.findByAccountIdElseThrow(accountId);

        return userInfoMapper.fromUser(user);
    }

    @Override
    public List<UserInfo> findAll() {
        List<User> users = userService.findAll();

        return userInfoMapper.fromUsers(users);
    }
}
