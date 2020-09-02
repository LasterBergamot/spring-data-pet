package com.pet.springdata.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String URL_OPEN_TRIVIA_DB_TOKEN = "https://opentdb.com/api_token.php?command=request";
    public static final String URL_OPEN_TRIVIA_DB_API = "https://opentdb.com/api.php?amount=%s&token=%s";

    public static final String EMPTY_STRING = "";

    public static final int MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST = 50;

    public static final String KEY_ANSWERED_CORRECTLY = "answeredCorrectly";
    public static final String KEY_USER = "user";

    public static final String SYMBOL_COLON_EQUAL = ":";

    public static final String CACHE_NAME_ANSWER_CACHE = "answerCache";
    public static final String CACHE_NAME_TRIVIA_CACHE = "triviaCache";
    public static final String CACHE_NAME_USER_CACHE = "userCache";

    public static final String JSON_PROPERTY_RESPONSE_CODE = "response_code";
    public static final String JSON_PROPERTY_RESULTS = "results";
    public static final String JSON_PROPERTY_CATEGORY = "category";
    public static final String JSON_PROPERTY_TYPE = "type";
    public static final String JSON_PROPERTY_DIFFICULTY = "difficulty";
    public static final String JSON_PROPERTY_QUESTION = "question";
    public static final String JSON_PROPERTY_CORRECT_ANSWER = "correct_answer";
    public static final String JSON_PROPERTY_INCORRECT_ANSWERS = "incorrect_answers";

    public static final String JSON_PROPERTY_RESPONSE_MESSAGE = "response_message";
    public static final String JSON_PROPERTY_TOKEN = "token";

    public static final String DEFAULT_FIRST_NAME = "defaultFirstName";
    public static final String DEFAULT_MIDDLE_NAME = "defaultMiddleName";
    public static final String DEFAULT_LAST_NAME = "defaultLastName";
    public static final String DEFAULT_PHONE_NUMBER = "06808888888";
}
