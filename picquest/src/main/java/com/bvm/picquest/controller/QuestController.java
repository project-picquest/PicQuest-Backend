package com.bvm.picquest.controller;

import com.bvm.picquest.dto.QuestSubmitForm;
import com.bvm.picquest.dto.User;
import com.bvm.picquest.service.QuestService;
import com.bvm.picquest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/quests")
public class QuestController {

    private final QuestService qs;
    private final UserService us;

    @Operation(summary = "오늘의 퀘스트, 완료한 퀘스트는 isCompleted가 true로 전송")
    @PostMapping
    public ResponseEntity<?> todaysCompleteQuestList(@Parameter(description = "사용자 이메일(아이디)", required = false) @RequestBody User user) {
        if (user.getEmail() == null) {
            return ResponseEntity.ok(qs.todaysQuestList());
        } else {
            return ResponseEntity.ok(qs.todaysCompleteQuestList(user.getEmail()));
        }
    }

    @Operation(summary = "퀘스트 제출")
    @PostMapping(value = "/submit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> submitQuest(@RequestPart QuestSubmitForm form, @Parameter(description = "제출할 사진") @RequestPart MultipartFile image) throws IOException {
        us.updateScore((int)(form.getScore() * 0.2), form.getUserEmail());
        return ResponseEntity.ok(qs.upsertImageLink(form, image));
    }

    @Operation(summary = "오늘의 퀘스트 상세 페이지")
    @GetMapping("/{index}")
    public ResponseEntity<?> questIndex(@PathVariable int index) {
        return ResponseEntity.ok(qs.findTodaysQuestByIndex(index));
    }
}
