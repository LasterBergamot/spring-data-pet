package com.pet.springdata.domain.trivia.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_RESPONSE_CODE;
import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_RESULTS;

@NoArgsConstructor
@Getter
public class OpenTriviaDatabaseResponse {

    @NonNull
    private String responseCode;

    @NonNull
    private List<OpenTriviaDatabaseResult> results;

    @JsonCreator
    public OpenTriviaDatabaseResponse(@JsonProperty(JSON_PROPERTY_RESPONSE_CODE) String responseCode, @JsonProperty(JSON_PROPERTY_RESULTS) List<OpenTriviaDatabaseResult> results) {
        this.responseCode = responseCode;
        this.results = results;
    }
}
