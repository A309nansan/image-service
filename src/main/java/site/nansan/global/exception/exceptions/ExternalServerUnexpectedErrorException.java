package site.nansan.global.exception.exceptions;

import site.nansan.global.exception.ErrorCode;
import site.nansan.global.exception.UserException;

public class ExternalServerUnexpectedErrorException extends UserException {
    public ExternalServerUnexpectedErrorException(ErrorCode errorCode) {
        super(errorCode);
    }
}
