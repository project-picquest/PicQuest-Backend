package com.bvm.picquest.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProfileForm {
    private String nickname;
    private boolean isMyself;
    private Long userScore;
    private List<ProfileQuestForm> completeQuestList;
}
