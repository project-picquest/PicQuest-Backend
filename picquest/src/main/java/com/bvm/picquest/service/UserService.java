package com.bvm.picquest.service;

import com.bvm.picquest.dto.User;
import com.bvm.picquest.dto.UserLoginForm;
import com.bvm.picquest.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private UserMapper um;

    // TODO: 시간 남으면 Exception 추가
    public User login(UserLoginForm form) {
        User loginUser = um.findByEmail(form.getEmail());
        if (loginUser.getPassword().equals(form.getPassword())) {
            return loginUser;
        } else {
            return null;
        }
    }
}
