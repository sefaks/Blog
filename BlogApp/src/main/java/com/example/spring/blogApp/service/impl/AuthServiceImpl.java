package com.example.spring.blogApp.service.impl;

import com.example.spring.blogApp.entity.Role;
import com.example.spring.blogApp.entity.User;
import com.example.spring.blogApp.exception.BlogAPIException;
import com.example.spring.blogApp.payload.LoginDto;
import com.example.spring.blogApp.payload.RegisterDto;
import com.example.spring.blogApp.repository.RoleRepository;
import com.example.spring.blogApp.repository.UserRepository;
import com.example.spring.blogApp.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService{

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public String login(LoginDto loginDto) {

       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
               (loginDto.getUserNameOrEmail(),loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User logged-in successfully ! ";
    }

    @Override
    public String register(RegisterDto registerDto) {

        if(userRepository.existsByUserName(registerDto.getUserName())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Username is already exists!");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST,"Email is already exist.");
        }
        User user = new User();
        user.setName(registerDto.getName());
        user.setUserName(registerDto.getUserName());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles= new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();


    }


}
