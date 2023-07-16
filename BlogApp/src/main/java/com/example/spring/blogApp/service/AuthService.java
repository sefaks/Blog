package com.example.spring.blogApp.service;

import com.example.spring.blogApp.payload.LoginDto;
import com.example.spring.blogApp.payload.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);

}
