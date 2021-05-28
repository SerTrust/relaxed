package com.chihyao.relaxed.module.messagelist.service;

import com.chihyao.relaxed.module.messagelist.entity.User;
import com.chihyao.relaxed.module.messagelist.entity.UserRequest;
import com.chihyao.relaxed.module.messagelist.entity.UserResponse;

public interface UserService {

    UserResponse createUser(UserRequest request);

    User getUserByAccount(String account);
}
