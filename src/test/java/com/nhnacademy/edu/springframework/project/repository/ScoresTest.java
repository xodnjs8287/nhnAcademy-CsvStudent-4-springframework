package com.nhnacademy.edu.springframework.project.repository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ScoresTest {


    @Test
    void load() {
        //given

        //when, then
        Scores scores = new CsvScores();
        assertDoesNotThrow(scores::load);
    }

    @Test
    void findAll() {
        //given
        Scores scoreRepository = new CsvScores();
        scoreRepository.load();

        //when
        List<Score> scores = scoreRepository.findAll();

        //then
        assertAll(
                () -> assertThat(scores).extracting(Score::getScore).containsExactlyInAnyOrder(30, 80, 70),
                () -> assertThat(scores).extracting(Score::getStudentSeq).containsExactlyInAnyOrder(1, 2, 3)
        );
    }
}