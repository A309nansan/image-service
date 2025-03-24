package site.nansan.global.exception.exceptions;

import site.nansan.global.exception.ErrorCode;
import site.nansan.global.exception.UserException;

public class ExternalServerTooManyRequestsException extends UserException {
    public ExternalServerTooManyRequestsException(ErrorCode errorCode) {
        super(errorCode);
    }
}

