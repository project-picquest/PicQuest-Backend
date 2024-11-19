package com.bvm.picquest.controller;

import com.bvm.picquest.dto.User;
import com.bvm.picquest.dto.UserLoginForm;
import com.bvm.picquest.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService us;

    @Operation(summary = "유저 로그인 메소드", description = "description")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginForm form, HttpSession session) {
        User loginUser = us.login(form);
        if (loginUser != null) {
            session.setAttribute("userInfo", loginUser);
            return ResponseEntity.status(HttpStatus.OK).body("login Complete");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("login Failed");
        }
    }
}
