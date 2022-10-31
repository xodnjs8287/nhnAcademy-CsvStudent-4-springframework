package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {


    private Students studentRepository = new CsvStudents();

    @Test
    @DisplayName("CsvStudents에서 데이터들을 로드한다.")
    void load() {

        assertDoesNotThrow(studentRepository::load);
    }

    @Test
    void findAll() {
        studentRepository.load();

        Collection<Student> students = studentRepository.findAll();

        assertAll(
                () -> assertThat(students).extracting(Student::getSeq).containsExactlyInAnyOrder(1, 2, 3, 4),
                () -> assertThat(students).extracting(Student::getName).containsExactlyInAnyOrder("A", "B", "A", "D")
        );
    }

    @Test
    void merge() {
        Students studentRepository = new CsvStudents();
        studentRepository.load();

        List<Score> scores = Arrays.asList(
                new Score(1, 1),
                new Score(2, 2),
                new Score(3, 3),
                new Score(4, 4)
        );

        studentRepository.merge(scores);

        Collection<Student> students = studentRepository.findAll();
        assertAll(
                () -> assertThat(students).extracting(Student::getSeq).containsExactlyInAnyOrder(1, 2, 3, 4),
                () -> assertThat(students).extracting(Student::getName).containsExactlyInAnyOrder("A", "B", "A", "D"),
                () -> assertThat(students).extracting(Student::getScore).extracting(Score::getScore).containsExactlyInAnyOrder(1, 2, 3, 4)
        );
    }
}