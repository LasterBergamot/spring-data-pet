package com.pet.springdata.domain.trivia.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class OpenTriviaDatabaseResult {

    @NonNull
    private String category;

    @NonNull
    private String type;

    @NonNull
    private String difficulty;

    @NonNull
    private String question;

    @NonNull
    private String correctAnswer;

    @NonNull
    private List<String> incorrectAnswers;

    @JsonCreator
    public OpenTriviaDatabaseResult(@JsonProperty("category") String category, @JsonProperty("type") String type, @JsonProperty("difficulty") String difficulty, @JsonProperty("question") String question,
                                    @JsonProperty("correct_answer") String correctAnswer, @JsonProperty("incorrect_answers") List<String> incorrectAnswers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }
}
