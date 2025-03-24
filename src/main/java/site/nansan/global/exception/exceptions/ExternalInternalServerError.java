package site.nansan.global.exception.exceptions;

import site.nansan.global.exception.ErrorCode;
import site.nansan.global.exception.UserException;

public class ExternalInternalServerError extends UserException {
    public ExternalInternalServerError(ErrorCode errorCode) {
        super(errorCode);
    }
}
