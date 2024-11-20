package com.bvm.picquest.controller;

import com.bvm.picquest.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> findByTitle(@Parameter(description = "검색창 내 검색어") @PathVariable String title) {
        return ResponseEntity.ok(as.findByTitle(title));
    }
}
