package com.bvm.picquest.dto;

import lombok.Data;

@Data
public class UserJoinForm {
    private String email;
    private String password;
    private String nickname;
}
