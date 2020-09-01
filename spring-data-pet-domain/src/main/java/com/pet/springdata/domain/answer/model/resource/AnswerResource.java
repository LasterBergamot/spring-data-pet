package com.pet.springdata.domain.answer.model.resource;

import com.pet.springdata.repository.trivia.model.Trivia;
import com.pet.springdata.repository.user.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Builder
public class AnswerResource implements Serializable {

    @NonNull
    private final Short id;

    @NonNull
    private final User user;

    @NonNull
    private final Trivia trivia;

    @NonNull
    private final String selectedAnswer;

    @NonNull
    private final Boolean answeredCorrectly;
}
