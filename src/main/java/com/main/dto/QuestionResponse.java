package com.main.dto;

import java.util.List;

import com.main.entity.Difficulty;
import com.main.entity.QuestionStatus;

public record QuestionResponse(
		
		 Long id,
		    String title,
		    String description,
		    String topic,
		    Difficulty difficulty,
		    QuestionStatus status,
		    List<TestCaseResponse> testCases) {

}
