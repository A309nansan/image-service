package site.nansan.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

<Service> 이름으로 클래스명을 리팩터링하고 주석처리

@Getter
@AllArgsConstructor
public class UserException extends RuntimeException{
    private ErrorCode errorCode;
}
