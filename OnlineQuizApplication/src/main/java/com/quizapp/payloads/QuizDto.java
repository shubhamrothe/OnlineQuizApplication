package com.quizapp.payloads;

import java.time.LocalDate;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuizDto {

   
    private Long id;

    @NotNull(message = "Subject name cannot be null")
    @Size(min = 2, max = 50, message = "Subject name must be between 2 and 50 characters")
    private String subjectName;

    @NotNull(message = "Description cannot be null")
    @Size(min = 10, max = 200, message = "Description must be between 10 and 200 characters")
    private String description;

    @NotNull(message = "Date cannot be null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Date must be today or in the future")
    private LocalDate date;

    @NotNull(message = "Marks cannot be null")
    @Positive(message = "Marks must be a positive value")
    private Double marks;

    @NotNull(message = "Total questions cannot be null")
    @Min(value = 10, message = "Total questions must be at least 10")
    private Integer totalQuestions;

    @NotNull(message = "Pass marks cannot be null")
    @Positive(message = "Pass marks must be a positive value")
    @DecimalMax(value = "100.0", message = "Pass marks cannot exceed total marks")
    private Double passMarks;

    @NotNull(message = "Level cannot be null")
    @Pattern(regexp = "Easy|Medium|Hard", 
             message = "Level must be one of the following: Easy, Medium, Hard")
    private String level;
}
