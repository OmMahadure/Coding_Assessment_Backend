package com.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.TestCase;

public interface TestCaseRepo extends JpaRepository<TestCase, Long>{
		
	List<TestCase> findByQuestion_IdOrderByExecutionOrderAsc(Long questionId);
	
	List<TestCase> findByQuestion_IdAndSampleTrueOrderByExecutionOrderAsc(
	        Long questionId
	    );

	    List<TestCase> findByQuestion_IdAndSampleFalseOrderByExecutionOrderAsc(
	        Long questionId
	    );
}
