package com.quizapp.services;



import java.util.List;

import com.quizapp.payloads.ResultDto;

public interface ResultServiceI {
    List<ResultDto> getAllResults();
    ResultDto saveResult(ResultDto resultDto);
    List<ResultDto> getResultsByUserId(Long userId);
}

