package com.main.dto;

import java.util.List;

import com.main.entity.Difficulty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuestionRequest(
		 @NotBlank @Size(max = 300) 
		 String title,
		 
		    @NotBlank 
		    String description,
		    
		    @NotBlank @Size(max = 100) 
		 String topic,
		 
		    @NotNull 
		    Difficulty difficulty,
		    
		    @NotEmpty 
		    List<@Valid TestCaseRequest> testCases) {

}
