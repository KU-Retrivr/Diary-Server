package com.example.diary.service;

import com.example.diary.dto.DiaryRequestDto;
import com.example.diary.dto.DiaryResponseDto;
import com.example.diary.model.Diary;
import com.example.diary.repository.DiaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public List<DiaryResponseDto> getAllDiaries() {
        return diaryRepository.findAll().stream()
                .map(this::toResponseDto)
                .toList();
    }

    public DiaryResponseDto createDiary(DiaryRequestDto requestDto) {
        Diary diary = new Diary(null, requestDto.getContent(), requestDto.getDate());
        Diary savedDiary = diaryRepository.save(diary);
        return toResponseDto(savedDiary);
    }

    public boolean deleteDiary(Long id) {
        if (!diaryRepository.existsById(id)) {
            return false;
        }

        diaryRepository.deleteById(id);
        return true;
    }

    private DiaryResponseDto toResponseDto(Diary diary) {
        return new DiaryResponseDto(diary.getId(), diary.getContent(), diary.getDate());
    }
}
