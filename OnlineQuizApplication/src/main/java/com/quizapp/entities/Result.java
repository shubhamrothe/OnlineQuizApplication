package com.quizapp.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Result {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long resultId;

    @Column(name = "result_status")
    private String status;

    @Column(name = "result_score")
    private Double score;

    @Column(name = "exam_date")
    private String examDate;

    @Column(name = "total_marks")
    private Double totalMarks;

    @Column(name = "total_questions")
    private Integer totalQuestions;

    @ManyToOne
    @JoinColumn(name = "subject_name")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

}
