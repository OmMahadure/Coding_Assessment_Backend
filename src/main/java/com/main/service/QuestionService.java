package com.main.service;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.main.dto.*;
import com.main.entity.*;
import com.main.repository.QuestionRepo;


@Service
public class QuestionService {
	private final QuestionRepo questionRepository;

    public QuestionService(QuestionRepo questionRepository) {
        this.questionRepository = questionRepository;
    }

    // Admin: create a question as DRAFT
    @Transactional
    public QuestionResponse createQuestion(QuestionRequest request) {

        Question question = new Question();

        question.setTitle(request.title());
        question.setDescription(request.description());
        question.setTopic(request.topic());
        question.setDifficulty(request.difficulty());
        question.setStatus(QuestionStatus.DRAFT);

        for (TestCaseRequest test : request.testCases()) {

            TestCase testCase = new TestCase();

            testCase.setInputData(test.inputData());
            testCase.setExpectedOutput(test.expectedOutput());
            testCase.setSample(test.sample());
            testCase.setMarks(test.marks());
            testCase.setExecutionOrder(test.executionOrder());

            question.addTestCase(testCase);
        }

        Question saved = questionRepository.save(question);

        return mapQuestion(saved, false);
    }

    // User: view only published questions
    @Transactional(readOnly = true)
    public List<QuestionResponse> getPublishedQuestions() {

        return questionRepository
            .findByStatus(QuestionStatus.PUBLISHED)
            .stream()
            .map(question -> mapQuestion(question, false))
            .toList();
    }

    // User: view one published question
    @Transactional(readOnly = true)
    public QuestionResponse getPublishedQuestion(Long id) {

        Question question = questionRepository
            .findByIdAndStatus(id, QuestionStatus.PUBLISHED)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Question not found"
            ));

        return mapQuestion(question, false);
    }

    // Admin: view all questions, including hidden test cases
    @Transactional(readOnly = true)
    public List<QuestionResponse> getAllQuestionsForAdmin() {

        return questionRepository.findAll()
            .stream()
            .map(question -> mapQuestion(question, true))
            .toList();
    }

    // Admin: publish a question
    @Transactional
    public QuestionResponse publishQuestion(Long id) {

        Question question = questionRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Question not found"
            ));

        question.setStatus(QuestionStatus.PUBLISHED);

        return mapQuestion(questionRepository.save(question), true);
    }

    private QuestionResponse mapQuestion(
        Question question, boolean includeHidden
    ) {

        List<TestCaseResponse> tests = question.getTestCases()
            .stream()
            .filter(test -> includeHidden || test.isSample())
            .map(test -> new TestCaseResponse(
                test.getId(),
                test.getInputData(),
                test.getExpectedOutput(),
                test.isSample(),
                test.getMarks(),
                test.getExecutionOrder()
            ))
            .toList();

        return new QuestionResponse(
            question.getId(),
            question.getTitle(),
            question.getDescription(),
            question.getTopic(),
            question.getDifficulty(),
            question.getStatus(),
            tests
        );
    }

}
