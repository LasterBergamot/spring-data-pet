package com.pet.springdata.domain.trivia.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.io.Serializable;
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
@Builder
public class OpenTriviaDatabaseResult implements Serializable {

    @ApiModelProperty(
            value = "The category of the trivia's question",
            name = "category",
            dataType = "String",
            example = "Science & Nature"
    )
    @NonNull
    private String category;

    @ApiModelProperty(
            value = "The type of the trivia's question",
            name = "type",
            dataType = "String",
            example = "boolean"
    )
    @NonNull
    private String type;

    @ApiModelProperty(
            value = "The difficulty of the trivia's question",
            name = "difficulty",
            dataType = "String",
            example = "medium"
    )
    @NonNull
    private String difficulty;

    @ApiModelProperty(
            value = "The question of the trivia",
            name = "question",
            dataType = "String",
            example = "Chickens can live without a head."
    )
    @NonNull
    private String question;

    @ApiModelProperty(
            value = "The correct answer to the trivia's question",
            name = "correctAnswer",
            dataType = "String",
            example = "True"
    )
    @NonNull
    private String correctAnswer;

    @ApiModelProperty(
            value = "All incorrect answers of the trivia's question",
            name = "incorrectAnswers",
            dataType = "String",
            example = "False"
    )
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
