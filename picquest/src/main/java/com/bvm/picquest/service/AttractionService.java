package com.bvm.picquest.service;

import com.bvm.picquest.dto.Attraction;
import com.bvm.picquest.mapper.AttractionMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class AttractionService {

    private AttractionMapper am;

    public List<Attraction> findByTitle(String title) {
        if (title == null) return am.findByTitle(title, new Random().nextInt(26535)); else return am.findByTitle(title, 0);
    }
}
