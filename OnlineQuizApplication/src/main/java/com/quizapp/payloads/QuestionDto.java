package com.quizapp.payloads;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuestionDto {

    private Long id;

    @NotNull(message = "Question text cannot be null")
    private String text;

    @NotNull(message = "Option one cannot be null")
    private String optionOne;

    @NotNull(message = "Option two cannot be null")
    private String optionTwo;

    @NotNull(message = "Option three cannot be null")
    private String optionThree;

    @NotNull(message = "Option four cannot be null")
    private String optionFour;

    @NotNull(message = "Correct answer cannot be null")
    @Pattern(regexp = "OptionOne|OptionTwo|OptionThree|OptionFour", 
             message = "Correct answer must be one of: OptionOne, OptionTwo, OptionThree, OptionFour")
    private String correctAnswer;

    @NotNull(message = "Subject name cannot be null")
    private String subjectName;

    @NotNull(message = "Quiz ID cannot be null")
    @Positive(message = "Quiz ID must be a positive value")
    private Long quizId;
}
