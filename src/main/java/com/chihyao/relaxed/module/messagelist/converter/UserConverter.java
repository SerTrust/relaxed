package com.chihyao.relaxed.module.messagelist.converter;

import com.chihyao.relaxed.module.messagelist.entity.User;
import com.chihyao.relaxed.module.messagelist.entity.UserRequest;
import com.chihyao.relaxed.module.messagelist.entity.UserResponse;

public class UserConverter {
    public static User toUser(UserRequest request) {
        User user = new User();
        user.setAccount(request.getAccount());
        user.setPassword(request.getPassword());
        user.setName(request.getName());
        user.setAuthorities(request.getAuthorities());

        return user;
    }

    public static UserResponse toUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setAccount(user.getAccount());
        response.setName(user.getName());
        response.setAuthorities(user.getAuthorities());

        return response;
    }
}
