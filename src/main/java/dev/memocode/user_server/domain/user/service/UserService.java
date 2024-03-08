package dev.memocode.user_server.domain.user.service;

import dev.memocode.user_server.domain.user.dto.UserCreateDTO;
import dev.memocode.user_server.domain.user.entity.User;
import dev.memocode.user_server.domain.user.repository.UserRepository;
import dev.memocode.user_server.exception.GlobalException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static dev.memocode.user_server.exception.GlobalErrorCode.*;

@Service
@Validated
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(@Valid UserCreateDTO dto) {

        if (isJoined(dto.getAccountId())) {
            throw new GlobalException(USER_ALREAY_EXISTS);
        }

        if (isAlreadyUsername(dto.getUsername())) {
            throw new GlobalException(USER_USERNAME_ALREAY_EXISTS);
        }

        User user = User.builder()
                .username(dto.getUsername())
                .nickname(dto.getNickname())
                .accountId(dto.getAccountId())
                .build();

        return userRepository.save(user);
    }

    private boolean isJoined(UUID accountId) {
        return findByAccountId(accountId).isPresent();
    }

    private boolean isAlreadyUsername(String username) {
        return findByUsername(username).isPresent();
    }

    public Optional<User> findByAccountId(
            @NotNull(message = "ACCOUNT_ID_NOT_NULL:accountId must not be null") UUID accountId) {
        return userRepository.findByAccountId(accountId);
    }

    public User findByAccountIdElseThrow(
            @NotNull(message = "ACCOUNT_ID_NOT_NULL:accountId must not be null") UUID accountId) {
        return findByAccountId(accountId)
                .orElseThrow(() -> new GlobalException(USER_NOT_FOUND));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
