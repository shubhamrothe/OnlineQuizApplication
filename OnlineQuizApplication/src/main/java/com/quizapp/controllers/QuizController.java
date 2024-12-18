package com.quizapp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.payloads.CustomApiResponse;
import com.quizapp.payloads.QuizDto;
import com.quizapp.services.QuizServiceI;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/quizzes")
@Slf4j
public class QuizController {

	@Autowired
	private QuizServiceI quizService;

	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary="Create a new Quiz")
	@PostMapping(value="/",consumes= {"application/json", "application/xml"}, produces={"application/json", "application/xml"})
	public ResponseEntity<QuizDto> createQuiz(@Valid @RequestBody QuizDto quizDto) {
		log.info("Requestiong to create a new Quiz");
		QuizDto createdQuiz = this.quizService.createQuiz(quizDto);
		log.info("Completed the request to create new Quiz");
		return new ResponseEntity<QuizDto>(createdQuiz, HttpStatus.CREATED);
	}

	@Operation(summary="Get the Quiz By Id")
	@GetMapping(value="/{id}", produces={"application/json", "application/xml"})
	public ResponseEntity<QuizDto> getQuizById(@PathVariable Long id) {
		log.info("Requesting to get Quiz by Id {} " + id);
		QuizDto quizDto = this.quizService.getQuizById(id);
		log.info("Completed the request to get Quiz by Id {} " + id);
		return new ResponseEntity<QuizDto>(quizDto, HttpStatus.OK);
	}

	@Operation(summary="Get all Quizess")
	@GetMapping(value="/", produces={"application/json", "application/xml"})
	public ResponseEntity<List<QuizDto>> getAllQuizzes() {
		log.info("Requesting to get all Quizzes");
		List<QuizDto> quizzes = quizService.getAllQuizzes();
		log.info("Completed the request to get all Quizzes");
		return new ResponseEntity<List<QuizDto>>(quizzes, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary="Delete the Quiz by Id")
	@DeleteMapping("/{id}")
	public ResponseEntity<CustomApiResponse> deleteQuiz(@PathVariable Long id) {
		log.info("Requesting to delete the Quiz by Id {} " + id);
		this.quizService.deleteQuiz(id);
		log.info("Completed the request to delete Quiz by Id {} " + id);
		return new ResponseEntity<>(new CustomApiResponse("Quiz deleted successfully !", true), HttpStatus.OK);
	}
}
