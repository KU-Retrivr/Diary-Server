package com.example.diary.repository;

import com.example.diary.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    Optional<Diary> findByDate(LocalDate date);

    List<Diary> findAllByDateBetweenOrderByDateAsc(LocalDate startDate, LocalDate endDate);

    void deleteByDate(LocalDate date);
}
