package com.pet.springdata.model.trivia;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenTriviaDatabaseToken {

    private String responseCode;
    private String responseMessage;
    private String token;

    public OpenTriviaDatabaseToken() {}

    @JsonCreator
    public OpenTriviaDatabaseToken(@JsonProperty("response_code") String responseCode, @JsonProperty("response_message") String responseMessage, @JsonProperty("token") String token) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.token = token;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
