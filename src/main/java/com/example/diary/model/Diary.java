package com.example.diary.model;

public class Diary {

    private Long id;
    private String content;
    private String date;

    public Diary() {
    }

    public Diary(Long id, String content, String date) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
