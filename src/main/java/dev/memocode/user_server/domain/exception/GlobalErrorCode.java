package dev.memocode.user_server.domain.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static dev.memocode.user_server.domain.exception.GlobalErrorCodeType.CRITICAL;
import static dev.memocode.user_server.domain.exception.GlobalErrorCodeType.INFO;
import static org.springframework.http.HttpStatus.*;

@Getter
public enum GlobalErrorCode {

    INTERNAL_ERROR(INTERNAL_SERVER_ERROR,500, "서버 에러, 관리자에게 문의하세요", CRITICAL),
    UNEXPECTED_API_RESPONSE(BAD_GATEWAY, 502, "예상치 못한 API 응답입니다.", CRITICAL),

    USER_USERNAME_ALREAY_EXISTS(BAD_REQUEST, 10000, "이미 존재하는 username입니다.", INFO),
    USER_ALREAY_EXISTS(BAD_REQUEST, 10001, "이미 존재하는 유저입니다.", INFO),
    USER_NOT_FOUND(NOT_FOUND, 404, "존재하지 않는 유저입니다.", INFO),
    OUTBOX_NOT_FOUND(NOT_FOUND, 404, "존재하지 않는 outbox입니다.", CRITICAL),
    ;

    private final HttpStatus status;
    private final int code;
    private final String message;
    private final GlobalErrorCodeType type;

    GlobalErrorCode(HttpStatus status, int code, String message, GlobalErrorCodeType type) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.type = type;
    }
}
