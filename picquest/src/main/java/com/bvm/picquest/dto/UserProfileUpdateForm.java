package com.bvm.picquest.dto;

import lombok.Data;

@Data
public class UserProfileUpdateForm {
    private String email;
    private String password;
    private String nickname;
    private String profileImage;
}
