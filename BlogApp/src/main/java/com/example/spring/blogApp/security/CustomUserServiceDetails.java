package com.example.spring.blogApp.security;

import com.example.spring.blogApp.entity.User;
import com.example.spring.blogApp.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserServiceDetails implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserServiceDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

       User user = userRepository.findByUserNameOrEmail(usernameOrEmail,usernameOrEmail).orElseThrow(
                ()-> new UsernameNotFoundException("User name not found with username or email: " + usernameOrEmail));

        Set<GrantedAuthority> authorities = user.getRoles().stream().map((role)->
        new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),authorities);


    }
}
