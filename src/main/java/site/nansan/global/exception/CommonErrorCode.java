package site.nansan.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Common Error Code: 모든 서비스에서 동일하게 존재 (수정 시 전파 부탁드립니다.)
 * Custom Error Code Naming Rule: <External Service Name>_<Http Status Code>_<Index>
 * ex. GLOBAL(내부 Error), MINIO, CONFIG, BUS ...
 * Http Status Code에 대한 자세한 설명
 * https://www.notion.so/Custom-Error-Code-HttpStatus-1c01696ab58480b8a937c095198c2103?pvs=4
 */

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {
    VALIDATION_FAILED(HttpStatus.OK, "GLOBAL_400_1", "Bad Request from Client"),

    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
