package com.example.finaltest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BoardDto {

    private String title;
    private String contents;
    private String userId;
    private String userName;
}
