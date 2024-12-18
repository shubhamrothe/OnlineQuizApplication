package com.quizapp.payloads;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResultDto {

    private Long id;
    
    @NotNull(message = "Status cannot be null")
    @Size(min = 1, max = 10, message = "Status must be between 1 and 10 characters")
    private String status;
    
    @NotNull(message = "Score cannot be null")
    @Min(value = 0, message = "Score must be at least 0")
    private Double score;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Exam date cannot be null")
    private String examDate;
    
    @NotNull(message = "Total marks cannot be null")
    @Min(value = 0, message = "Total marks must be at least 0")
    private Double totalMarks;
    
    @NotNull(message = "Total questions cannot be null")
    @Min(value = 1, message = "Total questions must be at least 1")
    private Integer totalQuestions;
    
    @NotNull(message = "Subject name cannot be null")
    private String subjectName;
    
    @NotNull(message = "User ID cannot be null")
    private Long userId;
    
    @NotNull(message = "Quiz ID cannot be null")
    private Long quizId;
    

}
