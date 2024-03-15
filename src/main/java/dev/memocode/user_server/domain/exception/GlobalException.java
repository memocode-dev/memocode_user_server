package dev.memocode.user_server.domain.exception;

import lombok.Getter;

@Getter
public class GlobalException extends RuntimeException {

    private final GlobalError error;

    public GlobalException(GlobalErrorCode code) {
        super(code.getMessage());
        this.error = GlobalError.of(code);
    }

    public GlobalException(GlobalErrorCode code, String message) {
        super(code.getMessage());
        this.error = GlobalError.of(code, message);
    }
}

