package com.pet.springdata.domain.answer.impl;

import com.pet.springdata.domain.answer.AnswerService;
import com.pet.springdata.domain.answer.model.resource.AnswerResource;
import com.pet.springdata.domain.answer.util.AnswerUtil;
import com.pet.springdata.repository.answer.AnswerRepository;
import com.pet.springdata.repository.answer.model.Answer;
import com.pet.springdata.repository.answer.specification.AnswerSpecification;
import com.pet.springdata.repository.criteria.SearchCriteria;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.pet.springdata.domain.util.Constants.KEY_ANSWERED_CORRECTLY;
import static com.pet.springdata.domain.util.Constants.KEY_USER;
import static com.pet.springdata.domain.util.Constants.SYMBOL_COLON_EQUAL;

@Service
@RequiredArgsConstructor
@Slf4j
@Cacheable(value = "answerCache")
public class TriviaAnswerService implements AnswerService {

    @NonNull
    private final AnswerRepository answerRepository;

    @Override
    @Transactional
    public AnswerResource saveAnswer(Answer answer) {
        log.info("Saving Answer: {}", answer);
        return AnswerUtil.transformAnswerToAnswerResource(answerRepository.save(answer));
    }

    @Override
    @Transactional
    @Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
    public List<AnswerResource> getAllAnswers() {
        log.info("Getting all Answers.");
        return AnswerUtil.transformAnswerListToAnswerResourceList(answerRepository.findAll());
    }

    @Override
    @Transactional
    public ResponseEntity<List<AnswerResource>> getAllAnswersDependingOnCorrectness(String answeredCorrectly) {
        log.info("Getting all Answers with correctness: {}", answeredCorrectly);
        SearchCriteria searchCriteria = new SearchCriteria(KEY_ANSWERED_CORRECTLY, SYMBOL_COLON_EQUAL, Boolean.parseBoolean(answeredCorrectly));
        AnswerSpecification answerSpecification = new AnswerSpecification(searchCriteria);

        List<Answer> answerList = answerRepository.findAll(answerSpecification);

        return ResponseEntity.ok(AnswerUtil.transformAnswerListToAnswerResourceList(answerList));
    }

    @Override
    @Transactional
    public ResponseEntity<List<AnswerResource>> getAllAnswersOfUser(String userId) {
        log.info("Getting all Answers of User with id: {}", userId);
        SearchCriteria searchCriteria = new SearchCriteria(KEY_USER, SYMBOL_COLON_EQUAL, Short.parseShort(userId));
        AnswerSpecification answerSpecification = new AnswerSpecification(searchCriteria);

        List<Answer> answerList = answerRepository.findAll(answerSpecification);

        return ResponseEntity.ok(AnswerUtil.transformAnswerListToAnswerResourceList(answerList));
    }

    @Override
    @Transactional
    public ResponseEntity<List<AnswerResource>> getAllAnswersDependingOnCorrectnessAndUserId(String answeredCorrectly, String userId) {
        log.info("Getting all Answers with correctness: {}, and UserId: {}", answeredCorrectly, userId);
        SearchCriteria searchCriteriaAnsweredCorrectly = new SearchCriteria(KEY_ANSWERED_CORRECTLY, SYMBOL_COLON_EQUAL, Boolean.parseBoolean(answeredCorrectly));
        AnswerSpecification answerSpecificationAnsweredCorrectly = new AnswerSpecification(searchCriteriaAnsweredCorrectly);

        SearchCriteria searchCriteriaUserId = new SearchCriteria(KEY_USER, SYMBOL_COLON_EQUAL, Short.parseShort(userId));
        AnswerSpecification answerSpecificationUserId = new AnswerSpecification(searchCriteriaUserId);

        List<Answer> answerList = answerRepository.findAll(Specification.where(answerSpecificationAnsweredCorrectly).and(answerSpecificationUserId));

        return ResponseEntity.ok(AnswerUtil.transformAnswerListToAnswerResourceList(answerList));
    }
}
