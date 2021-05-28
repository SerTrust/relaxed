package com.chihyao.relaxed.module.messagelist.service.Impl;

import com.chihyao.relaxed.module.messagelist.converter.UserConverter;
import com.chihyao.relaxed.module.messagelist.entity.User;
import com.chihyao.relaxed.module.messagelist.entity.UserRequest;
import com.chihyao.relaxed.module.messagelist.entity.UserResponse;
import com.chihyao.relaxed.module.messagelist.repository.UserRepository;
import com.chihyao.relaxed.module.messagelist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserResponse createUser(UserRequest request) {
        User user = UserConverter.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = userRepository.save(user);
        return UserConverter.toUserResponse(user);
    }

    @Override
    public User getUserByAccount(String account){
        return userRepository.findByAccount(account)
                .orElseThrow(() -> new RuntimeException());
    }
}
