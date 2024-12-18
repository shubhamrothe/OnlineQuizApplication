package com.quizapp.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.payloads.CustomApiResponse;
import com.quizapp.payloads.QuestionDto;
import com.quizapp.services.QuestionServiceI;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/questions")

@Slf4j
public class QuestionController {

    @Autowired
    private QuestionServiceI questionService;

    @Operation(summary="Add new Question")
    @PostMapping(value="/",consumes= {"application/json", "application/xml"}, produces={"application/json", "application/xml"})
    public ResponseEntity<QuestionDto> addQuestion(@Valid @RequestBody QuestionDto questionDto) {
    	log.info("Requesting to add new Question");
        QuestionDto createdQuestion = questionService.addQuestion(questionDto);
        log.info("Completed the request to add new Question");
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }
    
    @Operation(summary="Update the Question by ID")
    @PutMapping(value="/{id}",consumes= {"application/json", "application/xml"}, produces={"application/json", "application/xml"})
    public ResponseEntity<QuestionDto> updateQuestion(@PathVariable Long id, @RequestBody QuestionDto questionDto) {
    	log.info("Requesting to update the Question By Id {} "+ id);
        QuestionDto updatedQuestion = questionService.updateQuestion(id, questionDto);
        log.info("Completed the request to update the Question by Id {} "+ id);
        return new ResponseEntity<>(updatedQuestion, HttpStatus.OK);
    }
    
    @Operation(summary="Delete the Question by Id")    
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse> deleteQuestion(@PathVariable Long id) {
    	log.info("Requesting to delete the Question by Id {} "+ id);
        questionService.deleteQuestion(id);
        log.info("Completed the request to delete the Question by Id {}  "+ id);
        return new ResponseEntity<CustomApiResponse>(new CustomApiResponse("Question deleted successfully !", true), HttpStatus.OK);
    }
    
    @Operation(summary="Get all Questions by quizId")
    @GetMapping(value="/quiz/{quizId}", produces={"application/json", "application/xml"})
    public ResponseEntity<List<QuestionDto>> getQuestionsByQuizId(@PathVariable Long quizId) {
    	log.info("Requesting to get all Questions by quizId {} "+ quizId);
        List<QuestionDto> questions = questionService.getQuestionsByQuizId(quizId);
        log.info("Completed the request to get all Questions by quizId {} "+ quizId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }
    
    @Operation(summary="Get all Questions from database")
    @GetMapping(value="/", produces={"application/json", "application/xml"})
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
    	log.info("Requesting to get all Questions");
        List<QuestionDto> questions = questionService.getAllQuestions();
        log.info("Completed the request to get all Questions");
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }  
}

