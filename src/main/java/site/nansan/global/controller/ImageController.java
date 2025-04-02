package site.nansan.global.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.nansan.global.dto.UploadImageRequest;
import site.nansan.global.dto.UploadNumberImageRequest;
import site.nansan.global.service.ImageService;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@Valid @ModelAttribute UploadImageRequest request) {

        if (request.getChildId() == null || request.getChildId() == 0) {
            return ResponseEntity.badRequest().body("childId is required");
        }

        String hashedFileName = imageService.uploadImage(
                request.getChildId().toString(),
                request.getLocalDateTime(),
                request.getFile()
        );

        return ResponseEntity.ok(hashedFileName);
    }

    @PostMapping("/upload/number")
    public ResponseEntity<String> uploadNumberImage(@Valid @ModelAttribute UploadNumberImageRequest request) {

        if (request.getChildId() == null || request.getChildId() == 0) {
            return ResponseEntity.badRequest().body("childId is required");
        }

        String hashedFileName = imageService.uploadImage(
                request.getChildId().toString(),
                request.getLocalDateTime(),
                request.getNumber(),
                request.getFile()
        );

        return ResponseEntity.ok(hashedFileName);
    }

}
