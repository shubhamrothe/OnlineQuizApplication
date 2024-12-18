package com.quizapp.services;



import java.util.List;

import com.quizapp.payloads.SubjectDto;

public interface SubjectServiceI {

    List<SubjectDto> getAllSubjects();

    SubjectDto addSubject(SubjectDto subjectDto);

    String deleteSubject(String name);

    boolean isSubjectExists(String name);
}
