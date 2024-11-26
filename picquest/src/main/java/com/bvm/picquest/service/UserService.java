package com.bvm.picquest.service;

import com.bvm.picquest.dto.*;
import com.bvm.picquest.mapper.UserMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper um;
    private final S3Client s3Client;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    // TODO: 시간 남으면 Exception 추가
    public User login(UserLoginForm form) {
        User loginUser = um.findByEmail(form.getEmail());
        if (loginUser.getPassword().equals(form.getPassword())) {
            return loginUser;
        } else {
            return null;
        }
    }

    public int join(UserJoinForm form) {
        return um.insert(form);
    }

    public ProfileForm viewProfile(String email, String viewersEmail) {
        ProfileForm profile = um.findUserProfile(email, viewersEmail);
        List<ProfileQuestForm> quests = um.findUsersQuest(email);
        profile.setCompleteQuestList(quests);
        return profile;
    }

    public int updateProfile(UserProfileUpdateForm form, MultipartFile image) throws IOException {
        if (image != null) {
            String fileName = uploadImageToS3(image);
            form.setProfileImage(fileName);
        }
        return um.update(form);
    }

    public int updateScore(int score, String email) {
        return um.scoreUpdate(score, email);
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
