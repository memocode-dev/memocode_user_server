package dev.memocode.user_server.in.api.controller;

import dev.memocode.user_server.domain.user.dto.UserCreateDTO;
import dev.memocode.user_server.domain.user.dto.UserInfo;
import dev.memocode.user_server.in.api.form.UserCreateForm;
import dev.memocode.user_server.in.api.mapper.ApiUserMapper;
import dev.memocode.user_server.in.api.spec.UserApi;
import dev.memocode.user_server.usecase.UserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserUseCase userUseCase;

    private final ApiUserMapper apiUserMapper;

    private static final String USER_ID_CLAIM_NAME = "user_id";

    @PostMapping
    public ResponseEntity<UUID> createUser(@RequestBody @Valid UserCreateForm form, @AuthenticationPrincipal Jwt jwt) {
        UserCreateDTO userCreateDTO =
                apiUserMapper.userCreateFormAndUserId_to_userCreateDTO(
                        form, UUID.fromString(jwt.getClaim(USER_ID_CLAIM_NAME)));

        UUID userId = userUseCase.createUser(userCreateDTO);

        return ResponseEntity.created(URI.create(userId.toString()))
                .body(userId);
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfo> userInfo(@AuthenticationPrincipal Jwt jwt) {

        UserInfo userInfo = userUseCase.userInfo(UUID.fromString(jwt.getClaim(USER_ID_CLAIM_NAME)));

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping
    public ResponseEntity<List<UserInfo>> findAllUser() {
        List<UserInfo> userInfos = userUseCase.findAll();

        return ResponseEntity.ok(userInfos);
    }
}
