package com.pet.springdata.domain.trivia.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.List;

import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_CATEGORY;
import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_CORRECT_ANSWER;
import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_DIFFICULTY;
import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_INCORRECT_ANSWERS;
import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_QUESTION;
import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_TYPE;

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
    public OpenTriviaDatabaseResult(@JsonProperty(JSON_PROPERTY_CATEGORY) String category, @JsonProperty(JSON_PROPERTY_TYPE) String type,
                                    @JsonProperty(JSON_PROPERTY_DIFFICULTY) String difficulty, @JsonProperty(JSON_PROPERTY_QUESTION) String question,
                                    @JsonProperty(JSON_PROPERTY_CORRECT_ANSWER) String correctAnswer, @JsonProperty(JSON_PROPERTY_INCORRECT_ANSWERS) List<String> incorrectAnswers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;
    }
}
