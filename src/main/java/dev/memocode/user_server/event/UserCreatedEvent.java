package dev.memocode.user_server.event;

import dev.memocode.user_server.domain.user.dto.UserInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedEvent {

    @NotNull(message = "USER_INFO_NOT_NULL:userInfo must not be null")
    private UserInfo userInfo;
}
