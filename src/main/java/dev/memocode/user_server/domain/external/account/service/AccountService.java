package dev.memocode.user_server.domain.external.account.service;

import dev.memocode.user_server.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.UUID;

import static dev.memocode.user_server.exception.GlobalErrorCode.UNEXPECTED_API_RESPONSE;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final WebClient webClient;

    public void addAuthority(String name, UUID accountId) {

        try {
            ResponseEntity<Void> response = webClient.patch()
                    .uri("/api/accounts/" + accountId)
                    .contentType(APPLICATION_JSON)
                    .bodyValue(Map.of("authority", name))
                    .retrieve()
                    .toBodilessEntity()
                    .block();
            log.info("addAuthority() : {}", response);
        } catch (RuntimeException ex) {
            log.error("addAuthority() : {}", ex.getMessage());
            throw new GlobalException(UNEXPECTED_API_RESPONSE);
        }
    }
}
