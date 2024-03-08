package dev.memocode.user_server;

import dev.memocode.user_server.domain.user.dto.UserCreateDTO;
import dev.memocode.user_server.usecase.UserUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
class UserServerApplicationTests {

    @Autowired
    private UserUseCase userUseCase;

    @Test
    void contextLoads() {

        UserCreateDTO dto = UserCreateDTO.builder()
                .build();
        UUID user = userUseCase.createUser(dto);
    }

}
