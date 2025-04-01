package site.nansan.global.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<Object> handleFileUploadException(FileUploadException e) {

        log.warn("[{}]: \"{}\"", e.getErrorCode().getCode(), e.getErrorCode().getMessage());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    /**
     * [@Valid] [@RequestBody] 유효성 실패 처리
     * ex. @NotBlank(message = "Custom Message Content")
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request)
    {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );

        log.warn("Validation Failed: {}", errors);
        return ErrorResponse.toResponseEntity(CommonErrorCode.VALIDATION_FAILED, errors);
    }

//    @Override
//    protected ResponseEntity<Object> handleExceptionInternal(
//            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
//        return ErrorResponse.toResponseEntity((HttpStatus) statusCode);
//    }

    /**
     * [@RequestParam], [@PathVariable] 유효성 실패 처리
     * ex. @NotBlank(message = "Custom Message Content")
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        e.getConstraintViolations().forEach(violation -> {
                    errors.put(violation.getPropertyPath().toString(), violation.getMessage());
                });

        log.warn("ConstraintViolationException: {}", errors);
        return ErrorResponse.toResponseEntity(CommonErrorCode.VALIDATION_FAILED, errors);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleException(Exception e) {
//        return ErrorResponse.toResponseEntity(GlobalErrorCode.INTERNAL_SERVER_ERROR);
//    }
}
