
package com.main.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 100)
    private String topic;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Difficulty difficulty;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private QuestionStatus status=QuestionStatus.DRAFT;
    
    
    @OneToMany(
        mappedBy = "question",//tells JPA that TestCase.question owns the relationship.
        cascade = CascadeType.ALL,//saves or deletes associated test cases along with their question.
        orphanRemoval = true//deletes a test case removed from the question's collection.
    )
    @OrderBy("executionOrder ASC")
    private List<TestCase> testCases = new ArrayList<>();

    public Question() {
    }

    // Keeps both sides of the relationship synchronized
    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
        testCase.setQuestion(this);
    }

    public void removeTestCase(TestCase testCase) {
        testCases.remove(testCase);
        testCase.setQuestion(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public List<TestCase> getTestCases() {
        return testCases;
    }
    public QuestionStatus getStatus() {
        return status;
    }

    public void setStatus(QuestionStatus status) {
        this.status = status;
    }
    
}
