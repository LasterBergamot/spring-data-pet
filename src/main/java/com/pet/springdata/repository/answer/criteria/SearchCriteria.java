package com.pet.springdata.repository.answer.criteria;

import lombok.Getter;
import lombok.NonNull;

@Getter
public abstract class SearchCriteria {

    @NonNull
    protected String key;

    @NonNull
    protected String operation;
}
