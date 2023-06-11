package com.example.finaltest.service;

import com.example.finaltest.dto.UserDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDetailService {

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    List<UserDto> getAllUsers();

    UserDto getIdTokken(String uid) throws UsernameNotFoundException;

}