package com.main.dto;

public record TestCaseResponse(

		Long id,
		String inputData, 
		String expectedOutput, 
		boolean sample, 
		int marks, 
		int executionOrder) {
	

}
