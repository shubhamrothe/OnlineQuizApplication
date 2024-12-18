package com.quizapp.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.entities.Result;
import com.quizapp.payloads.ResultDto;
import com.quizapp.repositories.ResultRepository;
import com.quizapp.services.ResultServiceI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResultServiceImpl implements ResultServiceI {

	@Autowired
	private ResultRepository resultRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<ResultDto> getAllResults() {
		log.info("Fetching all results from the database.");

		List<ResultDto> results = this.resultRepository.findAll().stream().map(result -> {
			log.debug("Mapping Result entity to ResultDto for result ID: {}", result.getResultId());
			return modelMapper.map(result, ResultDto.class);
		}).collect(Collectors.toList());

		log.info("Successfully fetched {} results from the database.", results.size());
		return results;
	}

	@Override
	public ResultDto saveResult(ResultDto resultDto) {
		log.info("Initiating the process to save a new result.");
		log.debug("ResultDto details: {}", resultDto);

		Result result = modelMapper.map(resultDto, Result.class);
		log.debug("Mapped ResultDto to Result entity: {}", result);

		Result savedResult = this.resultRepository.save(result);
		log.info("Result saved successfully with ID: {}", savedResult.getResultId());

		ResultDto savedResultDto = modelMapper.map(savedResult, ResultDto.class);
		log.debug("Mapped saved Result entity to ResultDto: {}", savedResultDto);

		log.info("Completed the process to save a result with ID: {}", savedResult.getResultId());
		return savedResultDto;
	}

	@Override
	public List<ResultDto> getResultsByUserId(Long userId) {
		log.info("Fetching results for user with ID: {}", userId);

		List<ResultDto> userResults = this.resultRepository.findByUser_UserId(userId).stream().map(result -> {
			log.debug("Mapping Result entity to ResultDto for result ID: {}", result.getResultId());
			return modelMapper.map(result, ResultDto.class);
		}).collect(Collectors.toList());

		log.info("Successfully fetched {} results for user ID: {}", userResults.size(), userId);
		return userResults;
	}
}
