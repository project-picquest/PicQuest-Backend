package com.bvm.picquest.dto;

import lombok.Data;

@Data
public class UserScoreUpdateForm {
    private String email;
    private Long score;
}
