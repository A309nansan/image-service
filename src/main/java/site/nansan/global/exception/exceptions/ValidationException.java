package site.nansan.global.exception.exceptions;

import site.nansan.global.exception.ErrorCode;
import site.nansan.global.exception.UserException;

public class ValidationException extends UserException {
    public ValidationException(ErrorCode errorCode){super(errorCode);}
}
