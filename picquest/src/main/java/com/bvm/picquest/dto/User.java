package com.bvm.picquest.dto;

import lombok.Data;

@Data
public class User {
    private String email;
    private String password;
    private String nickname;
    private Long score;
    private String profileImage;
}
