package site.nansan.global.exception.exceptions;

import site.nansan.global.exception.ErrorCode;
import site.nansan.global.exception.UserException;

public class ExternalServerForbiddenException extends UserException {
    public ExternalServerForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
