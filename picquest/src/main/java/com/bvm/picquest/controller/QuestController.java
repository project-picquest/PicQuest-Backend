package com.bvm.picquest.controller;

import com.bvm.picquest.service.QuestService;
import io.swagger.v3.oas.annotations.Operation;
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

    private final QuestService qs;

    @Operation(summary = "오늘의 퀘스트, 완료한 퀘스트는 isCompleted가 true로 전송")
    @GetMapping
    public ResponseEntity<?> todaysCompleteQuestList(String email) {
        return ResponseEntity.ok(qs.todaysCompleteQuestList(email));
    }

    @Operation(summary = "퀘스트 제출")
    @PostMapping(value = "/submit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submitQuest(@RequestPart MultipartFile image) thuploadImageToS3(image);
        return ResponseEntity.ok("done");
    }
}
