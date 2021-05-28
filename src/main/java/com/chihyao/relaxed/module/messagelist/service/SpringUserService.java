package com.chihyao.relaxed.module.messagelist.service;

import com.chihyao.relaxed.module.messagelist.auth.SpringUser;
import com.chihyao.relaxed.module.messagelist.entity.User;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SpringUserService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        try {
            User user = userService.getUserByAccount(account);
            return new SpringUser(user);
        }catch (RuntimeException e){
            throw new UsernameNotFoundException("Account is wrong!");
        }
    }
}
