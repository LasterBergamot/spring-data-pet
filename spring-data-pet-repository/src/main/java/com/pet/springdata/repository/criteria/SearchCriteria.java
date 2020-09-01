package com.pet.springdata.repository.criteria;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class SearchCriteria {

    @NonNull
    private final String key;

    @NonNull
    private final String operation;

    @NonNull
    private final Object value;
}
