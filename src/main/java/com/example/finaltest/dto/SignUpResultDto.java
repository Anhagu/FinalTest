package com.example.finaltest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data // Getter, Setter, equals(), hashCode(), 그리고 toString() 메서드를 생성
@NoArgsConstructor // 매개변수 없는 생성자
@AllArgsConstructor // 전체 가지고 있는 생성자
@ToString // 객체의 문자열 표현을 반환하는 메서드, 클래스의 필드들을 자동으로 문자열로 변환
public class SignUpResultDto {
    private  boolean success;
    private int code;
    private String msg;
}

