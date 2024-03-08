package dev.memocode.user_server.form;

import dev.memocode.user_server.domain.user.validation.ValidNickname;
import dev.memocode.user_server.domain.user.validation.ValidUsername;
import lombok.Data;

@Data
public class UserCreateForm {

    @ValidUsername
    private String username;

    @ValidNickname
    private String nickname;
}
