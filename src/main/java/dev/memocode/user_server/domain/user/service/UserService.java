package dev.memocode.user_server.domain.user.service;

import dev.memocode.user_server.domain.user.entity.User;
import dev.memocode.user_server.domain.user.repository.UserRepository;
import dev.memocode.user_server.dto.UserCreateDTO;
import dev.memocode.user_server.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static dev.memocode.user_server.exception.GlobalErrorCode.*;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User createUser(UserCreateDTO dto) {

        if (isJoined(dto.getAccountId())) {
            throw new GlobalException(USER_ALREAY_EXISTS);
        }

        if (isAlreadyUsername(dto.getUsername())) {
            throw new GlobalException(USER_USERNAME_ALREAY_EXISTS);
        }

        User user = User.builder()
                .accountId(dto.getAccountId())
                .nickname(dto.getNickname())
                .username(dto.getUsername())
                .build();

        return userRepository.save(user);
    }

    private boolean isJoined(UUID accountId) {
        return findByAccountId(accountId).isPresent();
    }

    public Optional<User> findByAccountId(UUID accountId) {
        return userRepository.findByAccountId(accountId);
    }

    public User findByAccountIdElseThrow(UUID accountId) {
        return userRepository.findByAccountId(accountId)
                .orElseThrow(() -> new GlobalException(USER_NOT_FOUND));
    }

    private boolean isAlreadyUsername(String username) {
        return findByUsername(username).isPresent();
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
