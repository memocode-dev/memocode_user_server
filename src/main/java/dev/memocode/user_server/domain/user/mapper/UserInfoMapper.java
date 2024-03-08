package dev.memocode.user_server.domain.user.mapper;

import dev.memocode.user_server.domain.user.entity.User;
import dev.memocode.user_server.domain.user.dto.UserInfo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserInfoMapper {

    public UserInfo fromUser(User user) {
        return UserInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .build();
    }

    public List<UserInfo> fromUsers(List<User> users) {
        return users.stream()
                .map(this::fromUser)
                .toList();
    }
}
