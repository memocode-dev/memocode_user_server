package dev.memocode.user_server.domain.external.config;

import dev.memocode.user_server.domain.external.dto.TokenResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class WebClientConfig {

    @Value("${custom.api.domain}")
    private String apiDomain;

    @Value("${custom.auth.domain}")
    private String authDomain;

    @Value("${custom.auth.client-id}")
    private String authClientId;

    @Value("${custom.auth.client-secret}")
    private String authClientSecret;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(apiDomain)
                .filter(this::applyToken)
                .filter(logRequest())
                .build();
    }

    private Mono<ClientResponse> applyToken(ClientRequest request, ExchangeFunction next) {
        return fetchNewToken()
                .flatMap(tokenResponse -> proceedWithToken(request, next, tokenResponse));
    }

    public Mono<TokenResponse> fetchNewToken() {
        return WebClient.create(authDomain).post().uri("/oauth2/token")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData("grant_type", "client_credentials")
                        .with("client_id", authClientId)
                        .with("client_secret", authClientSecret))
                .retrieve()
                .bodyToMono(TokenResponse.class);
    }

    private Mono<ClientResponse> proceedWithToken(ClientRequest request, ExchangeFunction next, TokenResponse tokenResponse) {

        ClientRequest modifiedRequest = ClientRequest.from(request)
                .header("Authorization", "Bearer " + tokenResponse.getAccessToken())
                .build();

        return next.exchange(modifiedRequest);
    }

    // 요청 로깅을 위한 ExchangeFilterFunction
    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            // 요청 헤더 로깅
            clientRequest.headers().forEach((name, values) -> values.forEach(value -> log.info("{}: {}", name, value)));
            // 요청 메소드와 URL 로깅
            log.info("Request: {} {}", clientRequest.method(), clientRequest.url());
            // 바디는 이 방식으로 직접 로깅할 수 없습니다. ExchangeFilterFunction은 데이터 스트림을 소비하지 않으므로 바디 내용을 직접 볼 수 없습니다.
            return Mono.just(clientRequest);
        });
    }
}
