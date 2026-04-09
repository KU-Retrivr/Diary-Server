package com.example.diary.dto;

import java.time.LocalDate;

public class DiaryResponseDto {

    private Long id;
    private String content;
    private LocalDate date;

    public DiaryResponseDto() {
    }

    public DiaryResponseDto(Long id, String content, LocalDate date) {
        this.id = id;
        this.content = content;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
