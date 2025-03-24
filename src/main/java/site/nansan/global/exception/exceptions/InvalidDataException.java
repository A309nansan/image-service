package site.nansan.global.exception.exceptions;

import site.nansan.global.exception.ErrorCode;
import site.nansan.global.exception.UserException;

public class InvalidDataException extends UserException {
    public InvalidDataException(ErrorCode errorCode) {
        super(errorCode);
    }
}
