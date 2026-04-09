package com.example.diary.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class DiaryRequestDto {

    @NotNull
    private LocalDate date;

    private String emotion;

    @NotNull
    private String content;

    private String imageUrl;

    public DiaryRequestDto() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
