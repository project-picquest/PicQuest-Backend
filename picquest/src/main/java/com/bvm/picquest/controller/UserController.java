package com.bvm.picquest.controller;

import com.bvm.picquest.dto.*;
import com.bvm.picquest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService us;

    @Operation(summary = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody UserJoinForm form) {
        if (us.join(form) == 1) {
            return ResponseEntity.status(HttpStatus.OK).body("join complete!");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("join failed");
        }
    }

    @Operation(summary = "유저 로그인 메소드", description = "description")
    @PostMapping("/login")
    public ResponseEntity<?> login(@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "로그인 시 입력할 유저 아이디와 비밀번호") @RequestBody UserLoginForm form, HttpSession session) {
        User loginUser = us.login(form);
        if (loginUser != null) {
            session.setAttribute("userInfo", loginUser);
            return ResponseEntity.status(HttpStatus.OK).body(loginUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("login Failed");
        }
    }
    
    @Operation(summary = "로그아웃")
    @GetMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("logout complete!");
    }

    @Operation(summary = "유저 프로필 상세 페이지")
    @PostMapping("/profile/{email}")
    public ResponseEntity<?> viewProfile(@PathVariable String email, @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "이메일만 제대로 입력되면 됨") @RequestBody(required = false) User user) {
        String viewerEmail = user == null ? "" : user.getEmail();
        return ResponseEntity.ok(us.viewProfile(email, viewerEmail));
    }

    @Operation(summary = "프로필 수정")
    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateProfile(@RequestPart UserProfileUpdateForm form, @RequestPart MultipartFile image) throws IOException {
        return us.updateProfile(form, image) == 1 ? ResponseEntity.ok("update complete!") : ResponseEntity.ok("update Failed");
    }
}
