package com.example.diary.controller;

import com.example.diary.dto.DiaryRequestDto;
import com.example.diary.dto.DiaryResponseDto;
import com.example.diary.service.DiaryService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/diaries")
@CrossOrigin(origins = "*")
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping
    public List<DiaryResponseDto> getAllDiaries() {
        return diaryService.getAllDiaries();
    }

    @PostMapping
    public DiaryResponseDto createDiary(@Valid @RequestBody DiaryRequestDto diaryRequestDto) {
        return diaryService.createDiary(diaryRequestDto);
    }

    @DeleteMapping("/{id}")
    public String deleteDiary(@PathVariable Long id) {
        if (diaryService.deleteDiary(id)) {
            return "Diary deleted successfully.";
        }

        return "Diary not found.";
    }
}
