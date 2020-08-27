package com.pet.springdata.service.answer.impl;

import com.pet.springdata.repository.answer.AnswerRepository;
import com.pet.springdata.repository.criteria.SearchCriteria;
import com.pet.springdata.repository.answer.model.Answer;
import com.pet.springdata.repository.answer.specification.AnswerSpecification;
import com.pet.springdata.service.answer.AnswerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pet.springdata.util.Constants.KEY_ANSWERED_CORRECTLY;
import static com.pet.springdata.util.Constants.KEY_USER;
import static com.pet.springdata.util.Constants.SYMBOL_COLON_EQUAL;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
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
        SearchCriteria searchCriteria = new SearchCriteria(KEY_ANSWERED_CORRECTLY, SYMBOL_COLON_EQUAL, Boolean.parseBoolean(answeredCorrectly));
        AnswerSpecification answerSpecification = new AnswerSpecification(searchCriteria);

        return ResponseEntity.ok(answerRepository.findAll(answerSpecification));
    }

    @Override
    public ResponseEntity<List<Answer>> getAllAnswersOfUser(String userId) {
        log.info("Getting all Answers of User with id: {}", userId);
        SearchCriteria searchCriteria = new SearchCriteria(KEY_USER, SYMBOL_COLON_EQUAL, Short.parseShort(userId));
        AnswerSpecification answerSpecification = new AnswerSpecification(searchCriteria);

        return ResponseEntity.ok(answerRepository.findAll(answerSpecification));
    }

    @Override
    public ResponseEntity<List<Answer>> getAllAnswersDependingOnCorrectnessAndUserId(String answeredCorrectly, String userId) {
        log.info("Getting all Answers with correctness: {}, and UserId: {}", answeredCorrectly, userId);
        SearchCriteria searchCriteriaAnsweredCorrectly = new SearchCriteria(KEY_ANSWERED_CORRECTLY, SYMBOL_COLON_EQUAL, Boolean.parseBoolean(answeredCorrectly));
        AnswerSpecification answerSpecificationAnsweredCorrectly = new AnswerSpecification(searchCriteriaAnsweredCorrectly);

        SearchCriteria searchCriteriaUserId = new SearchCriteria(KEY_USER, SYMBOL_COLON_EQUAL, Short.parseShort(userId));
        AnswerSpecification answerSpecificationUserId = new AnswerSpecification(searchCriteriaUserId);


        return ResponseEntity.ok(answerRepository.findAll(Specification.where(answerSpecificationAnsweredCorrectly).and(answerSpecificationUserId)));
    }
}
