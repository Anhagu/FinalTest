package com.example.finaltest.dto;

public class UserDto { //다른 계층 간에 데이터를 전달하기 위해 사용되는 객체, 즉. 객체간 데이터 전송, 캡슐화, 변환해 전송하는 기능
    private Long id;
    private String uid;
    private String name;
    private String email;

    public UserDto(Long id, String uid, String name, String email) {
        this.id = id;
        this.uid = uid;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
