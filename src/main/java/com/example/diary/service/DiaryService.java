package com.example.diary.service;

import com.example.diary.dto.DiaryRequestDto;
import com.example.diary.dto.DiaryResponseDto;
import com.example.diary.exception.BadRequestException;
import com.example.diary.exception.ResourceNotFoundException;
import com.example.diary.model.Diary;
import com.example.diary.repository.DiaryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    @Transactional(readOnly = true)
    public List<DiaryResponseDto> getDiariesByMonth(String month) {
        if (month == null || month.isBlank()) {
            return diaryRepository.findAll().stream()
                    .map(this::toResponseDto)
                    .toList();
        }

        try {
            YearMonth yearMonth = YearMonth.parse(month);
            return diaryRepository.findAllByDateBetweenOrderByDateAsc(
                            yearMonth.atDay(1),
                            yearMonth.atEndOfMonth()
                    ).stream()
                    .map(this::toResponseDto)
                    .toList();
        } catch (DateTimeParseException exception) {
            throw new BadRequestException("month must be in YYYY-MM format.");
        }
    }

    @Transactional
    public DiaryResponseDto upsertDiary(LocalDate pathDate, DiaryRequestDto requestDto) {
        validateDate(pathDate, requestDto.getDate());

        Diary diary = diaryRepository.findByDate(pathDate)
                .orElseGet(Diary::new);

        diary.setDate(pathDate);
        diary.setEmotion(normalizeEmotion(requestDto.getEmotion()));
        diary.setContent(requestDto.getContent());
        diary.setImageUrl(requestDto.getImageUrl());

        Diary savedDiary = diaryRepository.save(diary);
        return toResponseDto(savedDiary);
    }

    @Transactional
    public void deleteDiaryByDate(LocalDate date) {
        Diary diary = diaryRepository.findByDate(date)
                .orElseThrow(() -> new ResourceNotFoundException("Diary not found for date: " + date));
        diaryRepository.delete(diary);
    }

    private void validateDate(LocalDate pathDate, LocalDate bodyDate) {
        if (bodyDate == null) {
            throw new BadRequestException("date is required.");
        }

        if (!pathDate.equals(bodyDate)) {
            throw new BadRequestException("Path date and body date must match.");
        }
    }

    private String normalizeEmotion(String emotion) {
        if (emotion == null || emotion.isBlank()) {
            return null;
        }

        return emotion;
    }

    private DiaryResponseDto toResponseDto(Diary diary) {
        return new DiaryResponseDto(
                diary.getId(),
                diary.getDate(),
                diary.getEmotion(),
                diary.getContent(),
                diary.getImageUrl()
        );
    }
}
