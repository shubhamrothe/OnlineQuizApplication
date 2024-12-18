package com.quizapp.controllers;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.payloads.ResultDto;
import com.quizapp.services.ResultServiceI;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/results")
@Slf4j
public class ResultController {

    @Autowired
    private ResultServiceI resultService;

    @Operation(summary="Create a Result")
    @PostMapping(value="/",consumes= {"application/json", "application/xml"}, produces={"application/json", "application/xml"})
    public ResponseEntity<ResultDto> addNewResult(@Valid @RequestBody ResultDto resultDto) {
    	log.info("Requesting to add new Result");
    	ResultDto saveResult = this.resultService.saveResult(resultDto);
    	log.info("Complete the request to add new Result");
        return new ResponseEntity<ResultDto>(saveResult, HttpStatus.CREATED);
    }
    
    @Operation(summary="Get Results")
    @GetMapping(value="/",produces={"application/json", "application/xml"})
    public ResponseEntity<List<ResultDto>> getAllResults() {
    	log.info("Requesting to get all Results");
    	List<ResultDto> allResults =this.resultService.getAllResults();
    	log.info("Completed the request to get all Results");
        return ResponseEntity.ok(allResults);
    }

    @Operation(summary="Get Result by user Id")
    @GetMapping(value="/user/{userId}", produces={"application/json", "application/xml"})
    public ResponseEntity<List<ResultDto>> getResultsByUser(@PathVariable Long userId) {
    	log.info("Requesting to get Result by userId {} "+ userId);
    	List<ResultDto> resultsByUserId = this.resultService.getResultsByUserId(userId);
    	log.info("Completed the request to get results by userId {} "+ userId);
        return new ResponseEntity<>(resultsByUserId, HttpStatus.OK);
    }
}
