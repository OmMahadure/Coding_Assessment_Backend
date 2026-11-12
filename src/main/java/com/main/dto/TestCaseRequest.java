package com.main.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TestCaseRequest(
    @NotNull 
    String inputData,
    
    @NotNull 
    String expectedOutput,
    
    boolean sample,
    
    @Min(1) 
    int marks,
    
    @Min(0) 
    int executionOrder
) {}