package site.nansan.global.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import site.nansan.global.exception.CustomErrorCode;
import site.nansan.global.exception.FileUploadException;
import site.nansan.global.util.MinioUtil;
import site.nansan.global.util.HashUtil;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    private final MinioUtil minioUtil;
    private static final int MAX_RETRIES = 3;

    // 기존: user-info 버킷, 경로: childId/hash
    public String uploadImage(String childId, String localDateTime, MultipartFile file) {
        String hashSource = childId + localDateTime;
        String hashedFileName = HashUtil.generateHash(hashSource);
        String objectPath = childId + "/" + hashedFileName;
        return uploadImageInternal("user-info", objectPath, file);
    }

    // 새로 추가: number 버킷, 경로: number/hash (여기서 number는 int를 String으로 변환)
    public String uploadImage(String childId, String localDateTime, int number, MultipartFile file) {
        String hashSource = childId + localDateTime + number;
        String hashedFileName = HashUtil.generateHash(hashSource);
        String objectPath = number + "/" + hashedFileName;
        return uploadImageInternal("number", objectPath, file);
    }

    // 공통 업로드 로직
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
//    public String uploadImage(String childId, String localDateTime, MultipartFile file) {
//        String hashedFileName = HashUtil.generateHash(childId, localDateTime);
//        String objectPath = childId + "/" + hashedFileName;
//        int attempts = 0;
//        while (attempts < MAX_RETRIES) {
//            try {
//                // 해싱된 파일명을 사용하여 이미지를 저장
//                minioUtil.uploadFile(file, objectPath);
//                log.info("이미지 업로드 성공: {}", objectPath);
//                return objectPath;
//            } catch (Exception e) {
//                attempts++;
//                log.error("이미지 업로드 시도 {} 실패: {}", attempts, e.getMessage());
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException ie) {
//                    Thread.currentThread().interrupt();
//                }
//                if (attempts >= MAX_RETRIES) {
//                    log.error("최대 재시도 횟수 초과");
//                    throw new FileUploadException(CustomErrorCode.UPLOAD_FAILED);
//                }
//            }
//        }
//        throw new FileUploadException(CustomErrorCode.UPLOAD_FAILED);
//    }
//    public String uploadImage(String childId, String localDateTime, int number, MultipartFile file) {
//        String hashedFileName = HashUtil.generateHash(childId, localDateTime);
//        String objectPath = childId + "/" + hashedFileName;
//        int attempts = 0;
//        while (attempts < MAX_RETRIES) {
//            try {
//                // 해싱된 파일명을 사용하여 이미지를 저장
//                minioUtil.uploadFile(file, objectPath);
//                log.info("이미지 업로드 성공: {}", objectPath);
//                return objectPath;
//            } catch (Exception e) {
//                attempts++;
//                log.error("이미지 업로드 시도 {} 실패: {}", attempts, e.getMessage());
//                try {
//                    Thread.sleep(500);
//                } catch (InterruptedException ie) {
//                    Thread.currentThread().interrupt();
//                }
//                if (attempts >= MAX_RETRIES) {
//                    log.error("최대 재시도 횟수 초과");
//                    throw new FileUploadException(CustomErrorCode.UPLOAD_FAILED);
//                }
//            }
//        }
//        throw new FileUploadException(CustomErrorCode.UPLOAD_FAILED);
//    }
}
