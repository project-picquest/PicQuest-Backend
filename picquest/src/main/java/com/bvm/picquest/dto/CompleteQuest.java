package com.bvm.picquest.dto;

import lombok.Data;

@Data
public class CompleteQuest {
    private String userEmail;
    private Long questId;
    private String img;
    private String title;
    private int score;

    public CompleteQuest(QuestSubmitForm form) {
        this.userEmail = form.getUserEmail();
        this.questId = form.getQuestId();
        this.title = form.getTitle();
        this.score = form.getScore();
    }
}
