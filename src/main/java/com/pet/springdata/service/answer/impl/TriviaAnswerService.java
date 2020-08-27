package com.pet.springdata.service.answer.impl;

import com.pet.springdata.repository.answer.criteria.ext.answered_correctly.SearchCriteriaForAnsweredCorrectly;
import com.pet.springdata.repository.answer.model.Answer;
import com.pet.springdata.repository.answer.AnswerRepository;
import com.pet.springdata.repository.answer.specification.answered_correctly.AnswerSpecificationForAnsweredCorrectly;
import com.pet.springdata.service.answer.AnswerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TriviaAnswerService implements AnswerService {

    @NonNull
    private final AnswerRepository answerRepository;

    @Override
    public void saveAnswer(Answer answer) {
        log.info("Saving Answer: {}", answer);
        answerRepository.save(answer);
    }

    @Override
    public ResponseEntity<List<Answer>> getAllAnswers() {
        log.info("Getting all Answers.");
        return ResponseEntity.ok(answerRepository.findAll());
    }

    @Override
    public ResponseEntity<List<Answer>> getAllAnswersDependingOnCorrectness(String answeredCorrectly) {
        log.info("Getting all Answers with correctness: {}", answeredCorrectly);
        SearchCriteriaForAnsweredCorrectly searchCriteriaForAnsweredCorrectly = new SearchCriteriaForAnsweredCorrectly("answeredCorrectly", ":", Boolean.parseBoolean(answeredCorrectly));
        AnswerSpecificationForAnsweredCorrectly answerSpecificationForAnsweredCorrectly = new AnswerSpecificationForAnsweredCorrectly(searchCriteriaForAnsweredCorrectly);

        return ResponseEntity.ok(answerRepository.findAll(answerSpecificationForAnsweredCorrectly));
    }

    @Override
    public ResponseEntity<List<Answer>> getAllAnswersOfUser(String userId) {
        log.info("Getting all Answers of User with id: {}", userId);

        return null;
    }
}
