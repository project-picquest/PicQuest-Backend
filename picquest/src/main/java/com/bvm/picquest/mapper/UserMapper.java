package com.bvm.picquest.mapper;

import com.bvm.picquest.dto.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    User findByEmail(String email);
}
