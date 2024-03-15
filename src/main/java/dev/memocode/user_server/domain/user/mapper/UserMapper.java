package dev.memocode.user_server.domain.user.mapper;

import dev.memocode.user_server.domain.user.dto.UserInfo;
import dev.memocode.user_server.domain.user.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapper {

    public UserInfo entityToUserInfo(User user) {
        return UserInfo.builder()
                .id(user.getId())
                .username(user.getUsername())
                .nickname(user.getNickname())
                .build();
    }

    public List<UserInfo> entityToUserInfo(List<User> users) {
        return users.stream()
                .map(this::entityToUserInfo)
                .toList();
    }
}
