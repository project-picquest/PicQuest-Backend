package com.bvm.picquest.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class QuestSubmitForm {
    private String userEmail;
    private Long questId;
    private String title;
    private int score;
}
