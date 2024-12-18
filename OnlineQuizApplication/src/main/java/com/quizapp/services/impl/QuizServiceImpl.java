package com.quizapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.entities.Quiz;
import com.quizapp.exceptions.ResourceNotFoundException;
import com.quizapp.payloads.QuizDto;
import com.quizapp.repositories.QuizRepository;
import com.quizapp.services.QuizServiceI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuizServiceImpl implements QuizServiceI {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public QuizDto createQuiz(QuizDto quizDto) {
        log.info("Initiating the process to create a new quiz");
        
        Quiz quiz = modelMapper.map(quizDto, Quiz.class);
        log.debug("Mapped QuizDto to Quiz entity: {}", quiz);

        Quiz savedQuiz = quizRepository.save(quiz);
        log.info("Quiz created successfully!");

        QuizDto savedQuizDto = modelMapper.map(savedQuiz, QuizDto.class);
        log.debug("Mapped saved Quiz entity to QuizDto: {}", savedQuizDto);

        return savedQuizDto;
    }

    @Override
    public QuizDto getQuizById(Long id) {
        log.info("Initiating the process to fetch quiz with Id: {}", id);

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Quiz not found with Id: {}", id);
                    return new ResourceNotFoundException("Quiz not found with Id: " + id);
                });

        log.info("Quiz found with Id: {}", id);
        QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
        log.debug("Mapped Quiz entity to QuizDto: {}", quizDto);

        return quizDto;
    }

    @Override
    public List<QuizDto> getAllQuizzes() {
        log.info("Fetching all quizzes from the database.");

        List<QuizDto> quizDtos = quizRepository.findAll()
                .stream()
                .map(quiz -> {
                    log.debug("Mapping Quiz entity to QuizDto for quiz Id: {}", quiz.getId());
                    return modelMapper.map(quiz, QuizDto.class);
                })
                .collect(Collectors.toList());

        log.info("Successfully fetched {} quizzes from the database.", quizDtos.size());
        return quizDtos;
    }

    @Override
    public void deleteQuiz(Long id) {
        log.info("Initiating the process to delete quiz with Id: {}", id);

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Quiz not found with Id: {}", id); 
                    return new ResourceNotFoundException("Quiz not found with Id: " + id);
                });

        quizRepository.delete(quiz);
        log.info("Quiz with Id: {} deleted successfully.", id);
    }
}
