package com.pet.springdata.domain.trivia.model.resource;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Builder
public class TriviaResource implements Serializable {

    @NonNull
    private final Short id;

    @NonNull
    private final String category;

    @NonNull
    private final String type;

    @NonNull
    private final String difficulty;

    @NonNull
    private final String question;

    @NonNull
    private final String correctAnswer;

    @NonNull
    private final List<String> incorrectAnswers;
}
