package site.nansan.global.util;

import io.minio.*;
import io.minio.http.Method;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MinioUtil {

    private final MinioClient minioClient;

    @Value("${minio.bucket.name}")
    private String BUCKET_NAME;

    public String uploadFile(MultipartFile file) throws Exception {
        createBucketIfNotExists();

        String newFileName = convertFileName(file.getOriginalFilename());
        InputStream inputStream = file.getInputStream();

        // 업로드
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(newFileName)
                        .stream(inputStream, file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build()
        );

        return newFileName; // 저장된 파일 이름 반환
    }

    public String getPreSignedUrl(String fileName) throws Exception {
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(fileName)
                        .method(Method.GET)
                        .expiry(10 * 60) // 10분 유효
                        .build()
        );
    }

    // 버킷이 존재하는지 확인하고 없으면 생성
    private void createBucketIfNotExists() throws Exception {

        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build())) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
        }
    }

    private String convertFileName(String originalFileName) throws Exception {

        if (originalFileName.isEmpty()) {
            throw new Exception();
        }

        return UUID.randomUUID() + originalFileName.substring(originalFileName.lastIndexOf("."));
    }
}
