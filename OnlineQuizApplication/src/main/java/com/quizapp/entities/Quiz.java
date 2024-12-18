package com.quizapp.entities;



import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quiz_desc", length = 500)
    private String description;

    @Column(name = "quiz_date")
    private LocalDate date;

    @Column(name = "quiz_marks")
    private Double marks;

    @Column(name = "quiz_total_questions")
    private Integer totalQuestions;

    @Column(name = "quiz_pass_marks")
    private Double passMarks;

    @Column(name = "quiz_level")
    private String level;
    
    @ManyToOne
    private Subject subject;


}

