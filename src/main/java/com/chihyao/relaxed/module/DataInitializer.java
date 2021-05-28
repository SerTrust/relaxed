package com.chihyao.relaxed.module;

import com.chihyao.relaxed.module.messagelist.entity.User;
import com.chihyao.relaxed.module.messagelist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @PostConstruct
    public void init() {
        User user = new User();
        user.setAccount("1983");
        user.setPassword(passwordEncoder.encode("1983"));
        user.setName("chihyao");

        userRepository.save(user);
    }
}
