package com.example.finaltest.dao;

import com.example.finaltest.entity.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    List<User> getAllUsersOrderedByNameAsc();
}
