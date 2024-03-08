package dev.memocode.user_server.api.spec;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import dev.memocode.user_server.domain.user.dto.UserInfo;
import dev.memocode.user_server.form.UserCreateForm;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;

import java.util.List;

@Tag(name = "users", description = "유저 API")
@SecurityRequirement(name = "bearer-key")
public interface UserApi {

    @Operation(summary = "유저 생성(회원가입)")
    ResponseEntity<Void> createUser(UserCreateForm form, Jwt jwt);

    @Operation(summary = "자신의 유저 정보 단건 조회")
    ResponseEntity<UserInfo> userInfo(Jwt jwt);

    @Operation(summary = "유저 전체조회")
    ResponseEntity<List<UserInfo>> findAllUser();
}
