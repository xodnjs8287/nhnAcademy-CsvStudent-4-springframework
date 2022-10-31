package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Scores;
import com.nhnacademy.edu.springframework.project.repository.Students;
import org.springframework.stereotype.Service;


@Service
public class CsvDataLoadService implements DataLoadService {

    private final Scores scoreRepository;
    private final Students studentRepository;


    public CsvDataLoadService(Scores scoreRepository, Students studentRepository) {
        this.scoreRepository = scoreRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void loadAndMerge() {
        scoreRepository.load();
        studentRepository.load();

        studentRepository.merge(scoreRepository.findAll());
    }
}
