package com.bvm.picquest.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class Quest {
    private Long id;
    private Date date;
    private String img;
}
