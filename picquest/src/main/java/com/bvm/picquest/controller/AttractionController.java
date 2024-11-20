package com.bvm.picquest.controller;

import com.bvm.picquest.service.AttractionService;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping
    public ResponseEntity<?> findByTitle() {
        return ResponseEntity.ok(as.findByTitle(null));
    }

    @Operation(summary = "제목에 맞는 여행지 반환")
    @GetMapping("/{title}")
    public ResponseEntity<?> findByTitle(@PathVariable String title) {
        return ResponseEntity.ok(as.findByTitle(title));
    }
}
