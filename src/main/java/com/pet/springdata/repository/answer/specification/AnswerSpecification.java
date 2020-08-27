package com.pet.springdata.repository.answer.specification;

import com.pet.springdata.repository.answer.model.Answer;
import com.pet.springdata.repository.criteria.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.pet.springdata.util.Constants.COLON_EQUAL_SYMBOL;
import static com.pet.springdata.util.Constants.GREATER_THAN_OR_EQUAL_SYMBOL;
import static com.pet.springdata.util.Constants.LESS_THAN_OR_EQUAL_SYMBOL;
import static com.pet.springdata.util.Constants.PERCENT_SYMBOL;

public class AnswerSpecification implements Specification<Answer> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<Answer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        String criteriaOperation = criteria.getOperation();
        String criteriaKey = criteria.getKey();
        String criteriaValue = criteria.getValue();
        Expression<String> criteriaKeyExpression = root.get(criteriaKey);

        if (criteriaOperation.equalsIgnoreCase(GREATER_THAN_OR_EQUAL_SYMBOL)) {
            return criteriaBuilder.greaterThanOrEqualTo(criteriaKeyExpression, criteriaValue);
        } else if (criteriaOperation.equalsIgnoreCase(LESS_THAN_OR_EQUAL_SYMBOL)) {
            return criteriaBuilder.lessThanOrEqualTo(criteriaKeyExpression, criteriaValue);
        } else if (criteriaOperation.equalsIgnoreCase(COLON_EQUAL_SYMBOL)) {
            if (criteriaKeyExpression.getJavaType() == String.class) {
                return criteriaBuilder.like(criteriaKeyExpression, PERCENT_SYMBOL + criteriaValue + PERCENT_SYMBOL);
            } else {
                return criteriaBuilder.equal(criteriaKeyExpression, criteriaValue);
            }
        }

        return null;
    }
}
