package com.pet.springdata.repository.answer.criteria.ext.answered_correctly;

import com.pet.springdata.repository.answer.criteria.SearchCriteria;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class SearchCriteriaForAnsweredCorrectly extends SearchCriteria {

    @NonNull
    private final boolean value;

    public SearchCriteriaForAnsweredCorrectly(String key, String operation, boolean value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
