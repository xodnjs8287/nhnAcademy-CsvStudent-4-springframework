package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GradeQueryServiceTest {

    Scores scoreRepository = new CsvScores();
    Students studentsRepository = new CsvStudents();

    @Test
    void getScoreByStudentName() {
        GradeQueryService gradeQueryService = new DefaultGradeQueryService(studentsRepository);
        new CsvDataLoadService(scoreRepository, studentsRepository).loadAndMerge();

        List<Score> scores = gradeQueryService.getScoreByStudentName("A");
        assertThat(scores).extracting(Score::getScore).containsExactlyInAnyOrder(30, 70);
    }

    @Test
    void getScoreByStudentSeq() {
        GradeQueryService gradeQueryService = new DefaultGradeQueryService(studentsRepository);
        new CsvDataLoadService(scoreRepository, studentsRepository).loadAndMerge();

        Score score = gradeQueryService.getScoreByStudentSeq(1);
        assertThat(score.getScore()).isEqualTo(30);
        assertThat(score.getStudentSeq()).isEqualTo(1);
    }
}