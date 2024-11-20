package com.bvm.picquest.service;

import com.bvm.picquest.config.S3Config;
import com.bvm.picquest.dto.QuestTransferForm;
import com.bvm.picquest.dto.User;
import com.bvm.picquest.mapper.QuestMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestMapper qm;
    private final S3Client s3Client;

    @Value("${cloud.aws.s3.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.s3.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<QuestTransferForm> todaysCompleteQuestList(String email) {
        return qm.findQuestTransferFormByDate(email, Date.valueOf(LocalDate.now()));
    }

    public String uploadImageToS3(MultipartFile image) throws IOException {
        String fileName = UUID.randomUUID().toString().concat(image.getOriginalFilename());
        InputStream inputStream = image.getInputStream();
        PutObjectRequest objectRequest = PutObjectRequest.builder()
                                                         .key(fileName)
                                                         .bucket(bucket)
                                                         .contentType(image.getContentType())
                                                         .build();

        try {
            s3Client.putObject(objectRequest, RequestBody.fromInputStream(inputStream, image.getSize()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
