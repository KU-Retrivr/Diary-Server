package com.example.diary.controller;

import com.example.diary.model.Diary;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/diaries")
@CrossOrigin(origins = "*")
public class DiaryController {

    private final List<Diary> diaries = new ArrayList<>();
    private Long nextId = 1L;

    @GetMapping
    public List<Diary> getAllDiaries() {
        return diaries;
    }

    @PostMapping
    public Diary createDiary(@RequestBody Diary diary) {
        Diary newDiary = new Diary(nextId++, diary.getContent());
        diaries.add(newDiary);
        return newDiary;
    }

    @DeleteMapping("/{id}")
    public String deleteDiary(@PathVariable Long id) {
        boolean removed = diaries.removeIf(diary -> diary.getId().equals(id));

        if (removed) {
            return "Diary deleted successfully.";
        }

        return "Diary not found.";
    }
}
