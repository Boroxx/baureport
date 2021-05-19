package com.boristenelsen.baureport.jwt.service;

import com.boristenelsen.baureport.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.boristenelsen.baureport.model.User user = userRepository.findUserByEmail(username);
        User securityUser;
        if(user!=null){
             securityUser = new User(user.getEmail(),user.getPassword(),new ArrayList<>());
        }else{
            throw new UsernameNotFoundException("User kann nicht gefunden werden");
        }
        return securityUser;
    }

    public com.boristenelsen.baureport.model.User getUser(String username){
        return userRepository.findUserByEmail(username);
    }
}
