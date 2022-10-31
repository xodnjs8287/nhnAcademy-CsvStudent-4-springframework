package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;


@Repository
public class CsvStudents implements Students {
    private Collection<Student> students;

    @Override
    public void load() {
        students = new ArrayList<>();
        try (
                InputStream inputStream = new ClassPathResource("data/student.csv").getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitStrings = line.split(",");
                int studentSeq = Integer.parseInt(splitStrings[0]);
                String studentName = splitStrings[1];
                students.add(new Student(studentSeq, studentName));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        if (Objects.isNull(students)) {
            return new ArrayList<>();
        }

        return students;
    }

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     *
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
        if (Objects.isNull(students) || Objects.isNull(scores)) {
            return;
        }

        Map<Integer, Score> studentSeqToScore = scores.stream().collect(Collectors.toMap(Score::getStudentSeq, it -> it));

        students.forEach(it -> {
            Score score = studentSeqToScore.get(it.getSeq());
            it.setScore(score);
        });
    }
}
