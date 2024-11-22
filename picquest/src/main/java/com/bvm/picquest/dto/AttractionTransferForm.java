package com.bvm.picquest.dto;

import lombok.Data;

import java.util.List;

@Data
public class AttractionTransferForm {
    private String title;
    private String firstImage1;
    private String addr1;
    private List<String> additionalPhotos;
}