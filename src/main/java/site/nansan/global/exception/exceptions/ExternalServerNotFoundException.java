package site.nansan.global.exception.exceptions;

import site.nansan.global.exception.ErrorCode;
import site.nansan.global.exception.UserException;

public class ExternalServerNotFoundException extends UserException {
    public ExternalServerNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
