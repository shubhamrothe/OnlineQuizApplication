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
import com.quizapp.payloads.SubjectDto;
import com.quizapp.services.SubjectServiceI;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1/subjects")
public class SubjectController {

	@Autowired
	private SubjectServiceI subjectService;

	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary = "Add new Subject")
	@PostMapping(value="/", consumes= {"application/json", "application/xml"}, produces={"application/json", "application/xml"})
	public ResponseEntity<SubjectDto> addNewSubject(@Valid @RequestBody SubjectDto subjectDto) {
		log.info("Requesting to add new subject");
		SubjectDto addSubject = this.subjectService.addSubject(subjectDto);
		log.info("Completed the request to add new subject");
		return new ResponseEntity<SubjectDto>(addSubject, HttpStatus.CREATED);

	}

	@Operation(summary="Get all Subjects")
	@GetMapping(value="/",  produces = {"application/json", "application/xml"})
	public ResponseEntity<List<SubjectDto>> getAllSubjects() {
		log.info("Requesting to retrive all Subjects");
		List<SubjectDto> allSubjects = this.subjectService.getAllSubjects();
		log.info("Completed the request to get all Subjects");
		return ResponseEntity.ok(allSubjects);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(summary="Delete Subject by name")
	@DeleteMapping("/{name}")
	public ResponseEntity<CustomApiResponse> deleteSubject(@PathVariable("name") String name) {
		log.info("Requesting to delete a Subject by name {}" + name);
		this.subjectService.deleteSubject(name);
		log.info("Completed the request to delete a Subject by name {}" + name);
		return new ResponseEntity<CustomApiResponse>(new CustomApiResponse("Subject deleted successfully !", true), HttpStatus.OK);
	}
}
