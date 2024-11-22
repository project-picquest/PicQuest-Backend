package com.bvm.picquest.mapper;

import com.bvm.picquest.dto.Attraction;
import com.bvm.picquest.dto.AttractionTransferForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AttractionMapper {
    List<Attraction> findByTitle(String title, int offset);
    AttractionTransferForm findByNo(int no);
    List<String> findImgsByNo(int no);
}
