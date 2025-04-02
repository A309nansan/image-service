package site.nansan.global.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import site.nansan.global.service.ImageService;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("childId") String childId,
                                              @RequestParam("localDateTime") LocalDateTime localDateTime,
                                              @RequestPart("file") MultipartFile file) {

        String hashedFileName = imageService.uploadImage(childId, localDateTime.toString(), file);

        return ResponseEntity.ok(hashedFileName);
    }

    @PostMapping("/upload/number")
    public ResponseEntity<String> uploadNumberImage(@RequestParam("childId") String childId,
                                                    @RequestParam("localDateTime") LocalDateTime localDateTime,
                                                    @RequestParam("number") int number,
                                              @RequestPart("file") MultipartFile file) {

        String hashedFileName = imageService.uploadImage(childId, localDateTime.toString(), number, file);

        return ResponseEntity.ok(hashedFileName);
    }
}
