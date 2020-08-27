package com.pet.springdata.repository.answer.specification.answered_correctly;

import com.pet.springdata.repository.answer.criteria.ext.answered_correctly.SearchCriteriaForAnsweredCorrectly;
import com.pet.springdata.repository.answer.model.Answer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.pet.springdata.util.Constants.SYMBOL_COLON_EQUAL;
import static com.pet.springdata.util.Constants.SYMBOL_PERCENT;

@RequiredArgsConstructor
public class AnswerSpecificationForAnsweredCorrectly implements Specification<Answer> {

    @NonNull
    @Transient
    private final transient SearchCriteriaForAnsweredCorrectly searchCriteriaForAnsweredCorrectly;

    @Override
    public Predicate toPredicate(Root<Answer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        String criteriaKey = searchCriteriaForAnsweredCorrectly.getKey();
        String criteriaOperation = searchCriteriaForAnsweredCorrectly.getOperation();
        boolean criteriaValue = searchCriteriaForAnsweredCorrectly.isValue();
        Expression<String> criteriaKeyExpression = root.get(criteriaKey);

        if (criteriaOperation.equalsIgnoreCase(SYMBOL_COLON_EQUAL)) {
            if (criteriaKeyExpression.getJavaType() == String.class) {
                return criteriaBuilder.like(criteriaKeyExpression, SYMBOL_PERCENT + criteriaValue + SYMBOL_PERCENT);
            } else {
                return criteriaBuilder.equal(criteriaKeyExpression, criteriaValue);
            }
        }

        return null;
    }
}
