package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class DataLoadServiceTest {


 Students student  = new CsvStudents();
    Scores scores= new CsvScores();


    @Test
    void loadAndMerge() {
        CsvDataLoadService csvDataLoadService = new CsvDataLoadService(scores, student);
        csvDataLoadService.loadAndMerge();

        scores.load();

        Collection<Student> students = student.findAll();
        List<Integer> scores = students.stream().map(Student::getScore).map(it -> {
            if (Objects.isNull(it)) {
                return null;
            }

            return it.getScore();
        }).collect(Collectors.toList());
        assertAll(
                () -> Assertions.assertThat(scores).containsExactlyInAnyOrder(30, 80, 70, null),
                () -> assertThat(students).extracting(Student::getSeq).containsExactlyInAnyOrder(1, 2, 3, 4),
                () -> assertThat(students).extracting(Student::getName).containsExactlyInAnyOrder("A", "B", "A", "D")
        );
    }
}