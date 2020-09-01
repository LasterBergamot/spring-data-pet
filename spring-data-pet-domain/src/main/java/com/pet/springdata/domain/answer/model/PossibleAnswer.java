package com.pet.springdata.domain.answer.model;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PossibleAnswer {

    @NonNull
    private final int indexOfAnswer;

    @NonNull
    private final String answer;

    @Override
    public String toString() {
        return String.format("#%d - %s", indexOfAnswer, answer);
    }
}
