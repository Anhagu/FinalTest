package com.example.finaltest.service;

import com.example.finaltest.dto.SignInResultDto;
import com.example.finaltest.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp (String id, String password, String name, String email, String role);
    SignInResultDto signIn (String id, String password) throws RuntimeException;
}