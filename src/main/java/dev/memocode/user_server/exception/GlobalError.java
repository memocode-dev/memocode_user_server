package dev.memocode.user_server.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
@AllArgsConstructor
public class GlobalError {

    private GlobalErrorCode code;

    private HttpStatus status;

    private String message;

    private String logMessage;

    public static GlobalError of(GlobalErrorCode code) {
        return GlobalError.builder()
                .code(code)
                .status(code.getStatus())
                .message(code.getMessage())
                .logMessage(code.getMessage())
                .build();
    }

    public static GlobalError of(GlobalErrorCode code, String logMessage) {
        return GlobalError.builder()
                .status(code.getStatus())
                .code(code)
                .message(code.getMessage())
                .logMessage(logMessage)
                .build();
    }
}
