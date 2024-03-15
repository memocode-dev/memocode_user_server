package dev.memocode.user_server.domain.user.service;

import dev.memocode.user_server.domain.exception.GlobalException;
import dev.memocode.user_server.domain.user.dto.UserCreateDTO;
import dev.memocode.user_server.domain.user.dto.UserInfo;
import dev.memocode.user_server.domain.user.entity.User;
import dev.memocode.user_server.domain.user.event.UserCreatedEvent;
import dev.memocode.user_server.domain.user.mapper.UserMapper;
import dev.memocode.user_server.domain.user.repository.UserRepository;
import dev.memocode.user_server.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static dev.memocode.user_server.domain.exception.GlobalErrorCode.USER_ALREAY_EXISTS;
import static dev.memocode.user_server.domain.exception.GlobalErrorCode.USER_USERNAME_ALREAY_EXISTS;

@Service
@Validated
@RequiredArgsConstructor
public class UserService implements UserUseCase {

    private final UserRepository userRepository;

    private final ApplicationEventPublisher eventPublisher;

    private final UserMapper userMapper;

    @Override
    public UUID createUser(UserCreateDTO dto) {

        if (isJoined(dto.getUserId())) {
            throw new GlobalException(USER_ALREAY_EXISTS);
        }

        if (isAlreadyUsername(dto.getUsername())) {
            throw new GlobalException(USER_USERNAME_ALREAY_EXISTS);
        }

        User user = User.builder()
                .id(dto.getUserId())
                .username(dto.getUsername())
                .nickname(dto.getNickname())
                .build();

        User savedUser = userRepository.save(user);

        // USER_CREATED 이벤트 발생
        UserCreatedEvent userCreatedEvent = UserCreatedEvent.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .build();
        eventPublisher.publishEvent(userCreatedEvent);

        return savedUser.getId();
    }

    @Override
    public UserInfo userInfo(UUID userId) {
        return null;
    }

    @Override
    public List<UserInfo> findAll() {
        List<User> users = userRepository.findAll();

        return userMapper.entityToUserInfo(users);
    }

    private boolean isJoined(UUID accountId) {
        return findById(accountId).isPresent();
    }

    private boolean isAlreadyUsername(String username) {
        return findByUsername(username).isPresent();
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private Optional<User> findById(UUID userId) {
        return userRepository.findById(userId);
    }
}
