package com.bvm.picquest.service;

import com.bvm.picquest.config.S3Config;
import com.bvm.picquest.dto.*;
import com.bvm.picquest.mapper.CompleteQuestMapper;
import com.bvm.picquest.mapper.QuestMapper;
import com.bvm.picquest.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestService {

    private final QuestMapper qm;
    private final CompleteQuestMapper cqm;
    private final S3Client s3Client;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<QuestTransferForm> todaysCompleteQuestList(String email) {
        return qm.findQuestTransferFormByDate(email, Date.valueOf(LocalDate.now()));
    }

    public List<QuestTransferForm> todaysQuestList() {
        return qm.findByDate(Date.valueOf(LocalDate.now()));
    }

    public String upsertImageLink(QuestSubmitForm form, MultipartFile image) throws IOException {
        String imgLink = uploadImageToS3(image);
        CompleteQuest completeQuestInfo = new CompleteQuest(form);
        completeQuestInfo.setImg(imgLink);

        if (cqm.findByEmailAndQuestId(completeQuestInfo) != 1) {
            cqm.insert(completeQuestInfo);
        } else {
            cqm.update(completeQuestInfo);
        }

        return imgLink;
    }

    public Quest findTodaysQuestByIndex(int index) {
        index -= 1;
        return qm.findTodaysQuestByIndex(index);
    }

    private String uploadImageToS3(MultipartFile image) throws IOException {
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
        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucket, region, fileName);
    }
}
