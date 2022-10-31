package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.*;
import org.junit.jupiter.api.Test;

class StudentServiceTest {

    Scores scoreRepository = new CsvScores();
    Students studentRepository = new CsvStudents();

    @Test
    void getPassedStudents() {
        new CsvDataLoadService(scoreRepository,studentRepository).loadAndMerge();
        StudentService studentService = new DefaultStudentService(studentRepository);

        studentService.getPassedStudents();


    }

    @Test
    void getStudentsOrderByScore() {
        new CsvDataLoadService(scoreRepository,studentRepository).loadAndMerge();
        StudentService studentService = new DefaultStudentService(studentRepository);


    }
}