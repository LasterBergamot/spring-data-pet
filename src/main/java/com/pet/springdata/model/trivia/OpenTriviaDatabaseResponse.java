package com.pet.springdata.model.trivia;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class OpenTriviaDatabaseResponse {

    private String responseCode;
    private List<TriviaDTO> results;

    public OpenTriviaDatabaseResponse() {}

    @JsonCreator
    public OpenTriviaDatabaseResponse(@JsonProperty("response_code") String responseCode, @JsonProperty("results") List<TriviaDTO> results) {
        this.responseCode = responseCode;
        this.results = results;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public List<TriviaDTO> getResults() {
        return results;
    }

    public void setResults(List<TriviaDTO> results) {
        this.results = results;
    }
}
