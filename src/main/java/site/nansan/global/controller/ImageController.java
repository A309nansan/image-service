package site.nansan.global.controller;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.nansan.global.service.ImageService;

import java.time.LocalDateTime;

@Validated
@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(
            @RequestParam("childId") @NotNull(message = "childId is required") String childId,
            @RequestParam("localDateTime") @NotNull(message = "localDateTime is required") LocalDateTime localDateTime,
            @RequestPart("file") @NotNull(message = "file is required") MultipartFile file) {

        String hashedFileName = imageService.uploadImage(childId, localDateTime.toString(), file);

        return ResponseEntity.ok(hashedFileName);
    }

    @PostMapping("/upload/number")
    public ResponseEntity<String> uploadNumberImage(
            @RequestParam("childId") @NotNull(message = "childId is required") String childId,
            @RequestParam("localDateTime") @NotNull(message = "localDateTime is required") LocalDateTime localDateTime,
            @RequestParam("number") @NotNull(message = "number is required")  Integer number,
            @RequestPart("file") @NotNull(message = "file is required") MultipartFile file) {

        String hashedFileName = imageService.uploadImage(childId, localDateTime.toString(), number, file);

        return ResponseEntity.ok(hashedFileName);
    }
}
