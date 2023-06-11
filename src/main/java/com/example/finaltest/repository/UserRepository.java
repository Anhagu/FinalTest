package com.example.finaltest.repository;

import com.example.finaltest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> { // UserRepository는 데이터에 접근해 읽고 쓰는 작업 수행

    User getByUid(String uid);

    List<User> findAll();




}
