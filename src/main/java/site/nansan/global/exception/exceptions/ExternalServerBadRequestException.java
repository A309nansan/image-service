package site.nansan.global.exception.exceptions;

import site.nansan.global.exception.ErrorCode;
import site.nansan.global.exception.UserException;

public class ExternalServerBadRequestException extends UserException {
    public ExternalServerBadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
