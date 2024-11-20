package com.bvm.picquest.controller;

import com.bvm.picquest.service.QuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quests")
public class QuestController {

    // TODO: 유저(or 퀘스트) 정보에 사진 저장하는 API 완성

    private final QuestService qs;

    @Operation(summary = "오늘의 퀘스트, 완료한 퀘스트는 isCompleted가 true로 전송")
    @GetMapping
    public ResponseEntity<?> todaysCompleteQuestList(@Parameter(description = "사용자 이메일(아이디)") String email) {
        return ResponseEntity.ok(qs.todaysCompleteQuestList(email));
    }

    @Operation(summary = "퀘스트 제출")
    @PostMapping(value = "/submit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submitQuest(@Parameter(description = "관광지 이름") @RequestPart("text") String title, @Parameter(description = "제출할 사진") @RequestPart("file") MultipartFile image) throws IOException {
        return ResponseEntity.ok(qs.uploadImageToS3(image));
    }
}
