package site.nansan.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * Custom Error Code: 특정 서비스에서만 존재하는 Error Code
 * Custom Error Code Naming Rule: <Service Name>_<Http Status Code>_<Index>
 * ex. User, Category ...
 * Http Status Code에 대한 자세한 설명
 * https://www.notion.so/Custom-Error-Code-HttpStatus-1c01696ab58480b8a937c095198c2103?pvs=4
 */

@Getter
@AllArgsConstructor
public enum CustomErrorCode implements ErrorCode{
    EXAMPLE_SUCCEEDED(HttpStatus.OK, "USER_200_1", "Detail Message for Explanation of Error"),
    EXAMPLE_FAILED(HttpStatus.OK, "CATEGORY_403_1", "Detail Message for Explanation of Error"),

    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
