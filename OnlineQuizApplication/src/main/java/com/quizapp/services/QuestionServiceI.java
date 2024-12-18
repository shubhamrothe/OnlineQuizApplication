package com.quizapp.services;



import java.util.List;

import com.quizapp.payloads.QuestionDto;

public interface QuestionServiceI {
    List<QuestionDto> getAllQuestions();
    QuestionDto addQuestion(QuestionDto questionDto);
    List<QuestionDto> getQuestionsByQuizId(Long quizId);
    QuestionDto updateQuestion(Long id, QuestionDto questionDto);
    void deleteQuestion(Long id);
    
   }
