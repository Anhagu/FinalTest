package com.example.finaltest.controller;

import com.example.finaltest.dao.UserDao;
import com.example.finaltest.dto.UserDto;
import com.example.finaltest.entity.User;
import com.example.finaltest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController { // Controller는 사용자와 상호작용하는 역할을 수행

    @Autowired
    private UserRepository userRepository;

    private final UserDao userDao;

    @GetMapping("/list")
    @PreAuthorize("hasRole('ROLE_ADMIN')") //호출 권한 검사하는 어노테이션
    public List<UserDto> getUserList() {
        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = userList.stream()
                .map(user -> new UserDto(user.getId(), user.getUid(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
        return userDtoList;
    }

    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/listOrderByName")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserDto> getUserListOrderedByName() {
        List<User> userList = userDao.getAllUsersOrderedByNameAsc();
        List<UserDto> userDtoList = userList.stream()
                .map(user -> new UserDto(user.getId(), user.getUid(), user.getName(), user.getEmail()))
                .collect(Collectors.toList());
        return userDtoList;
    }
}
