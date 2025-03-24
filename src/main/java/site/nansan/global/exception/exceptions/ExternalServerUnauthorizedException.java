package site.nansan.global.exception.exceptions;

import site.nansan.global.exception.ErrorCode;
import site.nansan.global.exception.UserException;

public class ExternalServerUnauthorizedException extends UserException {
    public ExternalServerUnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
