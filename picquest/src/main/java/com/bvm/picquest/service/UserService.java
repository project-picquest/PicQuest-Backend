package com.bvm.picquest.service;

import com.bvm.picquest.dto.*;
import com.bvm.picquest.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private UserMapper um;

    // TODO: 시간 남으면 Exception 추가
    public User login(UserLoginForm form) {
        User loginUser = um.findByEmail(form.getEmail());
        System.out.println(loginUser);
        if (loginUser.getPassword().equals(form.getPassword())) {
            return loginUser;
        } else {
            return null;
        }
    }

    public int join(UserJoinForm form) {
        return um.insert(form);
    }

    public ProfileForm viewProfile(String email, String viewersEmail) {
        ProfileForm profile = um.findUserProfile(email, viewersEmail);
        List<ProfileQuestForm> quests = um.findUsersQuest(email);
        profile.setCompleteQuestList(quests);
        return profile;
    }
}
