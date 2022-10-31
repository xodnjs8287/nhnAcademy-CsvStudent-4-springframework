package com.nhnacademy.edu.springframework.project.service;

import com.nhnacademy.edu.springframework.project.repository.Score;

public class Student {
    private final int seq;
    private final String name;
    private Score score;

    public Student(int seq, String name) {
        this.seq = seq;
        this.name = name;
    }

    public boolean isRightName(String name) {
        return this.name.equals(name);
    }

    public boolean isRightSeq(int seq) {
        return this.seq == seq;
    }

    public Score getScore() {
        return this.score;
    }
    public int getSeq() {
        return seq;
    }

    public String getName() {
        return name;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Student{" +
                "seq=" + seq +
                ", name='" + name + '\'' +
                ", score=" + score +
                '}' + '\n';
    }
}
