package com.example.finaltest.dto;

public class ChangeBoardDto {

    private Long id;
    private String title;
    private String contents;

    public ChangeBoardDto(Long id, String name) {
        this.id = id;
        this.title = title;
        this.contents = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
