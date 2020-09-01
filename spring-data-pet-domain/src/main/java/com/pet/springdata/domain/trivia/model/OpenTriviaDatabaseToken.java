package com.pet.springdata.domain.trivia.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_RESPONSE_CODE;
import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_RESPONSE_MESSAGE;
import static com.pet.springdata.domain.util.Constants.JSON_PROPERTY_TOKEN;

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
    public OpenTriviaDatabaseToken(@NonNull @JsonProperty(JSON_PROPERTY_RESPONSE_CODE) String responseCode, @NonNull  @JsonProperty(JSON_PROPERTY_RESPONSE_MESSAGE) String responseMessage,
                                   @NonNull  @JsonProperty(JSON_PROPERTY_TOKEN) String token) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.token = token;
    }
}
