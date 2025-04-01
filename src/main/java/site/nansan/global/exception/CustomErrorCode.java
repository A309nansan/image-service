package site.nansan.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode implements ErrorCode{
    EXAMPLE_SUCCEEDED(HttpStatus.OK, "USER_200_1", "Detail Message for Explanation of Error"),
    UPLOAD_FAILED(HttpStatus.OK, "IMAGE_403_1", "이미지를 업로드하는데 실패했습니다."),

    ;
    private final HttpStatus status;
    private final String code;
    private final String message;
}
