package com.quizapp.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quizapp.entities.Subject;
import com.quizapp.exceptions.ResourceNotFoundException;
import com.quizapp.payloads.SubjectDto;
import com.quizapp.repositories.SubjectRepository;
import com.quizapp.services.SubjectServiceI;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubjectServiceImpl implements SubjectServiceI {

	@Autowired
	private SubjectRepository subjectRepository;

	@Override
	public List<SubjectDto> getAllSubjects() {
		log.info("Fetching all subjects from the database.");
		List<Subject> subjects = (List<Subject>) this.subjectRepository.findAll();
		log.debug("Found {} subjects in the database.", subjects.size());

		List<SubjectDto> subjectDtos = new ArrayList<>();
		for (Subject subject : subjects) {
			subjectDtos.add(new SubjectDto(subject.getName()));
		}
		log.info("Successfully fetched all subjects.");
		return subjectDtos;
	}

	@Override
	public SubjectDto addSubject(SubjectDto subjectDto) {
		log.info("Initiating the process to add a new subject with name: {}", subjectDto.getName());

		if (this.subjectRepository.existsByName(subjectDto.getName())) {
			log.error("Subject with name '{}' already exists.", subjectDto.getName());
			throw new ResourceNotFoundException("Subject with name " + subjectDto.getName() + " already exists.");
		}

		Subject subject = new Subject();
		subject.setName(subjectDto.getName());
		subject = this.subjectRepository.save(subject);

		log.info("Subject with name '{}' has been added successfully.", subject.getName());
		return new SubjectDto(subject.getName());
	}

	@Override
	public String deleteSubject(String name) {
		log.info("Initiating the process to delete subject with name: {}", name);

		if (!this.subjectRepository.existsByName(name)) {
			log.error("Subject with name '{}' not found for deletion.", name);
			throw new ResourceNotFoundException("Subject with name " + name + " not found.");
		}

		int rowsDeleted = this.subjectRepository.deleteByName(name);
		log.info("Subject with name '{}' has been deleted. {} row(s) affected.", name, rowsDeleted);
		return rowsDeleted + " row(s) deleted";
	}

	@Override
	public boolean isSubjectExists(String name) {
		log.info("Checking if subject with name '{}' exists in the database.", name);
		boolean exists = this.subjectRepository.existsByName(name);
		log.debug("Subject existence check for '{}' returned: {}", name, exists);
		return exists;
	}
}
