package com.pet.springdata.repository.trivia.impl;

import com.pet.springdata.repository.trivia.TriviaRepositoryCustom;
import com.pet.springdata.repository.trivia.model.Trivia;
import com.pet.springdata.repository.trivia.model.Trivia_;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class TriviaRepositoryImpl implements TriviaRepositoryCustom {

    @NonNull
    private final EntityManager entityManager;

    @Override
    public List<Trivia> findTriviaByCategoryTypeAndDifficulty(String category, String type, String difficulty) {
        log.info("Finding Trivia with category: {}, type: {}, and difficulty: {}.", category, type, difficulty);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Trivia> criteriaQuery = criteriaBuilder.createQuery(Trivia.class);

        Root<Trivia> triviaRoot = criteriaQuery.from(Trivia.class);
        List<Predicate> predicates = new ArrayList<>();

        addValueToPredicatesIfItIsNotNull(predicates, criteriaBuilder, triviaRoot.get(Trivia_.category), category);
        addValueToPredicatesIfItIsNotNull(predicates, criteriaBuilder, triviaRoot.get(Trivia_.type), type);
        addValueToPredicatesIfItIsNotNull(predicates, criteriaBuilder, triviaRoot.get(Trivia_.difficulty), difficulty);

        criteriaQuery.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    private void addValueToPredicatesIfItIsNotNull(List<Predicate> predicates, CriteriaBuilder criteriaBuilder, Path<String> path, String value) {
        if (value != null) {
            predicates.add(criteriaBuilder.equal(path, value));
        }
    }
}
