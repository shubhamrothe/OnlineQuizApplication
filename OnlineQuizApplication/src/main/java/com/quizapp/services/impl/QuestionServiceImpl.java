package com.quizapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.entities.Question;
import com.quizapp.exceptions.ResourceNotFoundException;
import com.quizapp.payloads.QuestionDto;
import com.quizapp.repositories.QuestionRepository;
import com.quizapp.services.QuestionServiceI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionServiceI {

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<QuestionDto> getAllQuestions() {
		log.info("Fetching all questions from the database.");

		List<QuestionDto> questionDtos = questionRepository.findAll().stream().map(question -> {
			log.debug("Mapping Question entity with Id: {} and Text: '{}' to QuestionDto.", question.getId(),
					question.getText());
			return modelMapper.map(question, QuestionDto.class);
		}).collect(Collectors.toList());

		log.info("Successfully fetched {} questions from the database.", questionDtos.size());
		return questionDtos;
	}

	@Override
	public QuestionDto addQuestion(QuestionDto questionDto) {
		log.info("Initiating the process to add a new question with Text: '{}'", questionDto.getText());

		Question question = modelMapper.map(questionDto, Question.class);
		log.debug("Mapped QuestionDto to Question entity: {}", question);

		Question savedQuestion = questionRepository.save(question);
		log.info("Question added successfully with Id: {} and Text: '{}'", savedQuestion.getId(),
				savedQuestion.getText());

		QuestionDto savedQuestionDto = modelMapper.map(savedQuestion, QuestionDto.class);
		log.debug("Mapped saved Question entity to QuestionDto: {}", savedQuestionDto);

		return savedQuestionDto;
	}

	@Override
	public List<QuestionDto> getQuestionsByQuizId(Long quizId) {
		log.info("Fetching questions for quiz Id: {}", quizId);

		List<QuestionDto> questionDtos = questionRepository.findByQuizId(quizId).stream().map(question -> {
			log.debug("Mapping Question entity with Id: {} and Text: '{}' to QuestionDto.", question.getId(),
					question.getText());
			return modelMapper.map(question, QuestionDto.class);
		}).collect(Collectors.toList());

		log.info("Successfully fetched {} questions for quiz Id: {}", questionDtos.size(), quizId);
		return questionDtos;
	}

	@Override
	public QuestionDto updateQuestion(Long id, QuestionDto questionDto) {
		log.info("Initiating the process to update question with Id: {}", id);

		Question question = questionRepository.findById(id).orElseThrow(() -> {
			log.error("Question not found with Id: {}", id);
			return new ResourceNotFoundException("Question not found with Id: " + id);
		});

		log.debug("Found Question entity with Id: {} and Text: '{}'", question.getId(), question.getText());

		modelMapper.map(questionDto, question);
		log.debug("Updated Question entity: {}", question);

		Question updatedQuestion = questionRepository.save(question);
		log.info("Question updated successfully with Id: {} and Text: '{}'", updatedQuestion.getId(),
				updatedQuestion.getText());

		QuestionDto updatedQuestionDto = modelMapper.map(updatedQuestion, QuestionDto.class);
		log.debug("Mapped updated Question entity to QuestionDto: {}", updatedQuestionDto);

		return updatedQuestionDto;
	}

	@Override
	public void deleteQuestion(Long id) {
		log.info("Initiating the process to delete question with Id: {}", id);

		if (!questionRepository.existsById(id)) {
			log.error("Question not found with Id: {}", id);
			throw new ResourceNotFoundException("Question not found with Id: " + id);
		}

		questionRepository.deleteById(id);
		log.info("Question with Id: {} deleted successfully.", id);
	}
}
