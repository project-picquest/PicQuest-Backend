package com.bvm.picquest.mapper;

import com.bvm.picquest.dto.Attraction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionMapper {
    List<Attraction> findByTitle(String title, int offset);
}
