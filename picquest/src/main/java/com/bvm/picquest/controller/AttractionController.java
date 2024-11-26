package com.bvm.picquest.controller;

import com.bvm.picquest.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/attractions")
public class AttractionController {

    private AttractionService as;

    @Operation(summary = "검색어가 없을 때 반환하는 여행지(랜덤 9개 반환)")
    @GetMapping
    public ResponseEntity<?> findByTitle() {
        return ResponseEntity.ok(as.findByTitle(null));
    }

    @Operation(summary = "검색어에 맞는 여행지 반환(상위 9개 반환)")
    @GetMapping("/{title}")
    public ResponseEntity<?> findByTitle(@Parameter(description = "검색창 내 검색어") @PathVariable(required = false) String title) {
        return ResponseEntity.ok(as.findByTitle(title));
    }

    @GetMapping("/detail/{no}")
    public ResponseEntity<?> detailAttraction(@PathVariable int no) {
        return ResponseEntity.ok(as.attractionDetail(no));
    }

    @PostMapping(value = "/addPhoto", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> addAdditionalPhoto(@RequestPart Long attractionNo, @RequestPart MultipartFile image) throws IOException {
        return as.addAdditionalPhoto(attractionNo, image) == 1 ? ResponseEntity.ok("Add Photo Complete!") : ResponseEntity.ok("Add Photo Failed");
    }
}
