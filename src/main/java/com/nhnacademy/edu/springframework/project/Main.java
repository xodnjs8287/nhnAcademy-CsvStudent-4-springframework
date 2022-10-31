package com.nhnacademy.edu.springframework.project;

import com.nhnacademy.edu.springframework.project.service.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collection;

public class Main {

    // TODO 9 - 성공적으로 실행되어야 합니다.
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(YasConfig.class);
        DataLoadService csvDataLoadService = ac.getBean(DataLoadService.class);
        csvDataLoadService.loadAndMerge();
//        GradeQueryService bean = ac.getBean(GradeQueryService.class);
//        bean.getScoreByStudentName("A");
//        bean.getScoreByStudentSeq(1);
        StudentService bean1 = ac.getBean(StudentService.class);

        Collection<Student> passedStudents = bean1.getPassedStudents();
        System.out.println(passedStudents);
        Collection<Student> studentsOrderByScore = bean1.getStudentsOrderByScore();
        System.out.println(studentsOrderByScore);

    }
}
