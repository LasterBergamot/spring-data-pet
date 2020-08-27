package com.pet.springdata.repository.answer.specification.user_id;

import com.pet.springdata.repository.answer.criteria.ext.user_id.SearchCriteriaForUserId;
import com.pet.springdata.repository.answer.model.Answer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static com.pet.springdata.util.Constants.SYMBOL_COLON_EQUAL;
import static com.pet.springdata.util.Constants.SYMBOL_GREATER_THAN_OR_EQUAL_TO;
import static com.pet.springdata.util.Constants.SYMBOL_LESS_THAN_OR_EQUAL_TO;
import static com.pet.springdata.util.Constants.SYMBOL_PERCENT;

@RequiredArgsConstructor
public class AnswerSpecificationForUserId implements Specification<Answer> {

    @NonNull
    private final SearchCriteriaForUserId searchCriteriaForUserId;

    @Override
    public Predicate toPredicate(Root<Answer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        String criteriaKey = searchCriteriaForUserId.getKey();
        String criteriaOperation = searchCriteriaForUserId.getOperation();
        short criteriaValue = searchCriteriaForUserId.getValue();
        Expression<String> criteriaKeyExpression = root.get(criteriaKey);

        if (criteriaOperation.equalsIgnoreCase(SYMBOL_GREATER_THAN_OR_EQUAL_TO)) {
            return criteriaBuilder.greaterThanOrEqualTo(criteriaKeyExpression, String.valueOf(criteriaValue));
        } else if (criteriaOperation.equalsIgnoreCase(SYMBOL_LESS_THAN_OR_EQUAL_TO)) {
            return criteriaBuilder.lessThanOrEqualTo(criteriaKeyExpression, String.valueOf(criteriaValue));
        } else if (criteriaOperation.equalsIgnoreCase(SYMBOL_COLON_EQUAL)) {
            if (criteriaKeyExpression.getJavaType() == String.class) {
                return criteriaBuilder.like(criteriaKeyExpression, SYMBOL_PERCENT + criteriaValue + SYMBOL_PERCENT);
            } else {
                return criteriaBuilder.equal(criteriaKeyExpression, criteriaValue);
            }
        }

        return null;
    }
}
