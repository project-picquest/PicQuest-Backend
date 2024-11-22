package com.bvm.picquest.dto;

import lombok.Data;

@Data
public class CompleteQuest {
    private String userEmail;
    private Long questId;
    private String img;
    private int score;

    public CompleteQuest(QuestSubmitForm form) {
        this.userEmail = form.getUserEmail();
        this.questId = form.getQuestId();
        this.score = form.getScore();
    }
}
