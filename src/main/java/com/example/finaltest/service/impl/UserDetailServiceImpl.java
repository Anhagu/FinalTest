package com.example.finaltest.service.impl;

import com.example.finaltest.dto.ProductResponseDto;
import com.example.finaltest.dto.UserDto;
import com.example.finaltest.entity.Product;
import com.example.finaltest.entity.User;
import com.example.finaltest.repository.UserRepository;
import com.example.finaltest.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // 클래스를 서비스 빈으로 등록해줌
@RequiredArgsConstructor // 필수 매개변수를 생성자에다 만들어준다
public class UserDetailServiceImpl implements UserDetailService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.getByUid(username);
    }


    @Override
    public List<UserDto> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> new UserDto(user.getId(), user.getUid(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getIdTokken(String uid) throws UsernameNotFoundException {
        User user = userRepository.getByUid(uid);
        UserDto userDto = new UserDto(user.getId(), user.getUid(), user.getName(), user.getEmail());
        return userDto;
    }


}