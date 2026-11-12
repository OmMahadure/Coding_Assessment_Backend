package com.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.entity.Difficulty;
import com.main.entity.Question;
import com.main.entity.QuestionStatus;

public interface QuestionRepo extends JpaRepository<Question, Long>{
		
	List<Question> findByDifficulty(Difficulty difficulty);
	
	List<Question> findByTopicIgnoreCase(String topic);
	
	Optional<Question> findByIdAndDifficulty(Long id, Difficulty difficulty);
	
	List<Question> findByStatus(QuestionStatus status);
	
	Optional<Question> findByIdAndStatus(Long id, QuestionStatus status);
}
