package dev.memocode.user_server.api;

import lombok.RequiredArgsConstructor;
import dev.memocode.user_server.api.spec.UserApi;
import dev.memocode.user_server.dto.UserCreateDTO;
import dev.memocode.user_server.dto.UserInfo;
import dev.memocode.user_server.dto.form.UserCreateForm;
import dev.memocode.user_server.mapper.UserCreateDTOMapper;
import dev.memocode.user_server.usecase.UserUseCase;
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

    private final UserCreateDTOMapper userCreateDTOMapper;

    private static final String ACCOUNT_ID_CLAIM_NAME = "account_id";

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody UserCreateForm form, @AuthenticationPrincipal Jwt jwt) {
        UserCreateDTO userCreateDTO =
                userCreateDTOMapper.fromUserCreateFormAndAccountId(form, UUID.fromString(jwt.getClaim(ACCOUNT_ID_CLAIM_NAME)));

        UUID userId = userUseCase.createUser(userCreateDTO);

        return ResponseEntity.created(URI.create(userId.toString())).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfo> userInfo(@AuthenticationPrincipal Jwt jwt) {

        UserInfo userInfo = userUseCase.userInfo(UUID.fromString(jwt.getClaim(ACCOUNT_ID_CLAIM_NAME)));

        return ResponseEntity.ok(userInfo);
    }

    @GetMapping
    public ResponseEntity<List<UserInfo>> findAllUser() {
        List<UserInfo> userInfos = userUseCase.findAll();

        return ResponseEntity.ok(userInfos);
    }
}
