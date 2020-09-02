package com.pet.springdata.domain.answer.model.resource;

import com.pet.springdata.repository.trivia.model.Trivia;
import com.pet.springdata.repository.user.model.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Builder
public class AnswerResource implements Serializable {

    @ApiModelProperty(
            value = "The id of the answer stored in the database",
            name = "id",
            dataType = "Short",
            example = "1"
    )
    @NonNull
    private final Short id;

    @ApiModelProperty(
            value = "The user whom answered the given trivia's question",
            name = "user",
            dataType = "User",
            example = "  {\n" +
                    "    \"id\": 1,\n" +
                    "    \"name\": \"Andrew Henry Mourdough\",\n" +
                    "    \"phoneNumbers\": \"+36307654129, +36709123548\"\n" +
                    "  }"
    )
    @NonNull
    private final User user;

    @ApiModelProperty(
            value = "The trivia which was answered by the user",
            name = "trivia",
            dataType = "Trivia",
            example = "  {\n" +
                    "    \"category\": \"Animals\",\n" +
                    "    \"correctAnswer\": \"Canis Lupus\",\n" +
                    "    \"difficulty\": \"hard\",\n" +
                    "    \"id\": 1,\n" +
                    "    \"incorrectAnswers\": \"Canis Aureus, Canis Latrans, Canis Lupus Lycaon\",\n" +
                    "    \"question\": \"What is the Gray Wolf's scientific name?\",\n" +
                    "    \"type\": \"multiple\"\n" +
                    "  }"
    )
    @NonNull
    private final Trivia trivia;

    @ApiModelProperty(
            value = "The answer selected by the user",
            name = "selectedAnswer",
            dataType = "String",
            example = "Canis Lupus"
    )
    @NonNull
    private final String selectedAnswer;

    @ApiModelProperty(
            value = "The indication if the user answered correctly to the trivia's question or not",
            name = "answeredCorrectly",
            dataType = "Boolean",
            example = "true"
    )
    @NonNull
    private final Boolean answeredCorrectly;
}
