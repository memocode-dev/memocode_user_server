package dev.memocode.user_server.in.api.exception;

import dev.memocode.user_server.domain.exception.GlobalError;
import dev.memocode.user_server.domain.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ApiGlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex) {

        log.error("서버 에러 발생", ex);

        ErrorResponse response = ErrorResponse.builder()
                .code(500)
                .codeString("INTERNAL_ERROR")
                .message("서버 에러, 관리자에게 문의하세요")
                .build();

        return ResponseEntity.status(500).body(response);
    }

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> globalException(GlobalException ex) {

        GlobalError error = ex.getError();

        ErrorResponse response = ErrorResponse.builder()
                .code(error.getCode().getCode())
                .codeString(error.getCode().name())
                .message(error.getMessage())
                .build();

        return ResponseEntity.status(error.getStatus()).body(response);
    }
}
