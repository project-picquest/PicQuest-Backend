package com.bvm.picquest.service;

import com.bvm.picquest.dto.AdditionalPhoto;
import com.bvm.picquest.dto.Attraction;
import com.bvm.picquest.dto.AttractionTransferForm;
import com.bvm.picquest.mapper.AttractionMapper;
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
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttractionService {

    private final AttractionMapper am;
    private final S3Client s3Client;

    @Value("${cloud.aws.region.static}")
    private String region;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    public List<Attraction> findByTitle(String title) {
        if (title == null) return am.findByTitle(title, new Random().nextInt(18590)); else return am.findByTitle(title, 0);
    }

    public AttractionTransferForm attractionDetail(int no) {
        AttractionTransferForm form = am.findByNo(no);
        List<String> imgs = am.findImgsByNo(no);
        form.setAdditionalPhotos(imgs);

        return form;
    }

    public int addAdditionalPhoto(Long attractionNo, MultipartFile image) throws IOException {
        String fileName = uploadImageToS3(image);
        AdditionalPhoto additionalPhoto = new AdditionalPhoto();
        additionalPhoto.setAttractionNo(attractionNo);
        additionalPhoto.setImg(fileName);
        return am.addAdditionalPhoto(additionalPhoto);
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
