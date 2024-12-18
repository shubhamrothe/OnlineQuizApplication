package com.quizapp.services;


import java.util.List;

import com.quizapp.payloads.QuizDto;

public interface QuizServiceI {
    QuizDto createQuiz(QuizDto quizDto);
    QuizDto getQuizById(Long id);
    List<QuizDto> getAllQuizzes();
    void deleteQuiz(Long id);
}


