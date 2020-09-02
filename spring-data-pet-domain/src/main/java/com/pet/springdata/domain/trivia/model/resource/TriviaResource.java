package com.pet.springdata.domain.trivia.model.resource;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(
            value = "The id of the trivia stored in the database",
            name = "id",
            dataType = "Short",
            example = "1"
    )
    @NonNull
    private final Short id;

    @ApiModelProperty(
            value = "The category of the trivia's question",
            name = "category",
            dataType = "String",
            example = "Animals"
    )
    @NonNull
    private final String category;

    @ApiModelProperty(
            value = "The type of the trivia's question",
            name = "type",
            dataType = "String",
            example = "multiple"
    )
    @NonNull
    private final String type;

    @ApiModelProperty(
            value = "The difficulty of the trivia's question",
            name = "difficulty",
            dataType = "String",
            example = "hard"
    )
    @NonNull
    private final String difficulty;

    @ApiModelProperty(
            value = "The trivia's question",
            name = "question",
            dataType = "String",
            example = "What is the Gray Wolf's scientific name?"
    )
    @NonNull
    private final String question;

    @ApiModelProperty(
            value = "The correct answer for the trivia's question",
            name = "correctAnswer",
            dataType = "String",
            example = "Canis Lupus"
    )
    @NonNull
    private final String correctAnswer;

    @ApiModelProperty(
            value = "All incorrect answers for the trivia's question",
            name = "incorrectAnswers",
            dataType = "List<String>",
            example = "Canis Aureus, Canis Latrans, Canis Lupus Lycaon"
    )
    @NonNull
    private final List<String> incorrectAnswers;
}
