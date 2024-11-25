package com.bvm.picquest.mapper;

import com.bvm.picquest.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User findByEmail(String email);
    int insert(UserJoinForm form);
    ProfileForm findUserProfile(String email, String viewerEmail);
    List<ProfileQuestForm> findUsersQuest(String email);
    int update(UserProfileUpdateForm form);
    int scoreUpdate(int score, String email);
}
