package com.bvm.picquest.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
public class QuestTransferForm {
    private Long id;
    private String img;
    private boolean isCompleted;
}
