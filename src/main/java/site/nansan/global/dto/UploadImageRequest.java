package site.nansan.global.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
public class UploadImageRequest {

    @NotNull(message = "childId is required")
    private Integer childId;

    @NotNull(message = "localDateTime is required")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime localDateTime;

    @NotNull(message = "file is required")
    private MultipartFile file;

}
