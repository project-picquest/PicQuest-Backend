package com.bvm.picquest.mapper;

import com.bvm.picquest.dto.ProfileForm;
import com.bvm.picquest.dto.ProfileQuestForm;
import com.bvm.picquest.dto.User;
import com.bvm.picquest.dto.UserJoinForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByEmail(String email);
    int insert(UserJoinForm form);
    ProfileForm findUserProfile(String email, String viewerEmail);
    List<ProfileQuestForm> findUsersQuest(String email);
}
