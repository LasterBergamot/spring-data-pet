package com.pet.springdata.domain.trivia.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor
@Getter
public class OpenTriviaDatabaseToken {

    @NonNull
    private String responseCode;

    @NonNull
    private String responseMessage;

    @NonNull
    private String token;

    @JsonCreator
    public OpenTriviaDatabaseToken(@NonNull @JsonProperty("response_code") String responseCode, @NonNull  @JsonProperty("response_message") String responseMessage,
                                   @NonNull  @JsonProperty("token") String token) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.token = token;
    }
}
