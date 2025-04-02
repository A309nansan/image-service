package site.nansan.global.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.nansan.global.exception.CustomErrorCode;
import site.nansan.global.exception.FileUploadException;
import site.nansan.global.util.MinioUtil;
import site.nansan.global.util.HashUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final MinioUtil minioUtil;
    private static final int MAX_RETRIES = 3;

    public String uploadImage(String childId, LocalDateTime localDateTime, MultipartFile file) {
        String hashSource = childId + localDateTime.toString();
        String hashedFileName = HashUtil.generateHash(hashSource);
        String objectPath = localDateTime.toLocalDate() + "/" + hashedFileName;

        log.info(childId);
        log.info(String.valueOf(localDateTime));
        log.info(String.valueOf(file));
        log.info(hashSource);
        log.info(hashedFileName);
        log.info(objectPath);


        return uploadImageInternal("solved-problems", objectPath, file);
    }

    public String uploadNumberImage(String childId, LocalDateTime localDateTime, int number, MultipartFile file) {
        String hashSource = childId + localDateTime.toString() + number;
        String hashedFileName = HashUtil.generateHash(hashSource);
        String objectPath = number + "/" + hashedFileName;

        log.info(childId);
        log.info(String.valueOf(localDateTime));
        log.info(String.valueOf(file));
        log.info(hashSource);
        log.info(hashedFileName);
        log.info(objectPath);

        return uploadImageInternal("number", objectPath, file);
    }

    private String uploadImageInternal(String bucketName, String objectPath, MultipartFile file) {
        int attempts = 0;
        while (attempts < MAX_RETRIES) {
            try {
                minioUtil.uploadFile(bucketName, file, objectPath);
                log.info("이미지 업로드 성공: {}", objectPath);
                return objectPath;
            } catch (Exception e) {
                attempts++;
                log.error("이미지 업로드 시도 {} 실패: {}", attempts, e.getMessage());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                }
                if (attempts >= MAX_RETRIES) {
                    log.error("최대 재시도 횟수 초과");
                    throw new FileUploadException(CustomErrorCode.UPLOAD_FAILED);
                }
            }
        }
        throw new FileUploadException(CustomErrorCode.UPLOAD_FAILED);
    }

}
