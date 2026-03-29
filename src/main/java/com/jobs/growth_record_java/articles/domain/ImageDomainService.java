package com.jobs.growth_record_java.articles.domain;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

@Service
public class ImageDomainService {

    private final String uploadDir = "uploads";

    private final S3Presigner s3Presigner;
    private final S3Client s3Client;
    private final String bucketName;
    private final String region;

    public ImageDomainService(
        @Value("${aws.s3.bucket}") String bucketName,
        @Value("${aws.s3.region}") String region
    ) {
        this.bucketName = bucketName;
        this.region = region;
        Region awsRegion = Region.of(region);
        this.s3Presigner = S3Presigner.builder()
            .region(awsRegion)
            .build();
        this.s3Client = S3Client.builder()
            .region(awsRegion)
            .build();
    }

    public String saveImage(MultipartFile file) {

        if (file.isEmpty()) {
            throw new RuntimeException("ファイルが空です");
        }

        String contentType = file.getContentType();
        if (contentType == null ||
            !(contentType.equals("image/jpeg") ||
              contentType.equals("image/png") ||
              contentType.equals("image/webp"))) {

            throw new RuntimeException("jpeg, png, webpのみアップロード可能");
        }

        try {
            Files.createDirectories(Paths.get(uploadDir));

            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir, fileName);

            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            return "http://localhost:8080/uploads/" + fileName;

        } catch (IOException e) {
            throw new RuntimeException("画像保存失敗");
        }
    }

    public String generatePresignedUploadUrl(String fileName, String contentType) {

        if (fileName == null || fileName.isBlank()) {
            throw new RuntimeException("ファイル名は必須です");
        }

        String key = UUID.randomUUID() + "_" + fileName;

        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .contentType(contentType)
            .build();

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
            .putObjectRequest(objectRequest)
            .signatureDuration(Duration.ofMinutes(10))
            .build();

        PresignedPutObjectRequest presignedRequest = s3Presigner.presignPutObject(presignRequest);

        return presignedRequest.url().toString();
    }

    /**
     * バックエンド経由でS3にアップロード（CORS回避用）
     */
    public String uploadToS3(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("ファイルが空です");
        }

        String contentType = file.getContentType();
        if (contentType == null ||
            !(contentType.equals("image/jpeg") ||
              contentType.equals("image/png") ||
              contentType.equals("image/webp"))) {
            throw new RuntimeException("jpeg, png, webpのみアップロード可能");
        }

        String key = UUID.randomUUID() + "_" + file.getOriginalFilename();

        PutObjectRequest objectRequest = PutObjectRequest.builder()
            .bucket(bucketName)
            .key(key)
            .contentType(contentType)
            .build();

        try {
            s3Client.putObject(
                objectRequest,
                RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
        } catch (IOException e) {
            throw new RuntimeException("S3アップロード失敗", e);
        }

        return String.format("https://%s.s3.%s.amazonaws.com/%s",
            bucketName, region, key);
    }
}