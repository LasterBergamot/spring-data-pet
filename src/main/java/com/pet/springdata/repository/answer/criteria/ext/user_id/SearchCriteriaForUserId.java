package com.pet.springdata.repository.answer.criteria.ext.user_id;

import com.pet.springdata.repository.answer.criteria.SearchCriteria;
import lombok.Getter;
import lombok.NonNull;

@Getter
public class SearchCriteriaForUserId extends SearchCriteria {

    @NonNull
    private final short value;

    public SearchCriteriaForUserId(String key, String operation, short value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }
}
