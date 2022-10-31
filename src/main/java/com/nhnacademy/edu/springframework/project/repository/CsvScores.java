package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Repository
public class CsvScores implements Scores {

    private Collection<Score> scores;

   public CsvScores() {
    }

    /**
     * TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        scores = new ArrayList<>();
        try (
                InputStream inputStream = new ClassPathResource("data/score.csv").getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitStrings = line.split(",");
                int studentSeq = Integer.parseInt(splitStrings[0]);
                int score = Integer.parseInt(splitStrings[1]);
                scores.add(new Score(studentSeq, score));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Score> findAll() {
        if (Objects.isNull(scores)) {
            return new ArrayList<>();
        }

        return new ArrayList<>(scores);
    }
}
