package com.pet.springdata.model.trivia;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@NoArgsConstructor
@Getter
public class OpenTriviaDatabaseResponse {

    @NonNull
    private String responseCode;

    @NonNull
    private List<TriviaDTO> results;

    @JsonCreator
    public OpenTriviaDatabaseResponse(@JsonProperty("response_code") String responseCode, @JsonProperty("results") List<TriviaDTO> results) {
        this.responseCode = responseCode;
        this.results = results;
    }
}
