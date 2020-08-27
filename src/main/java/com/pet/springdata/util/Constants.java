package com.pet.springdata.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String URL_OPEN_TRIVIA_DB_TOKEN = "https://opentdb.com/api_token.php?command=request";
    public static final String URL_OPEN_TRIVIA_DB_API = "https://opentdb.com/api.php?amount=%s&token=%s";

    public static final String EMPTY_STRING = "";

    public static final int MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST = 50;

    public static final String SYMBOL_GREATER_THAN_OR_EQUAL_TO = ">";
    public static final String SYMBOL_LESS_THAN_OR_EQUAL_TO = "<";
    public static final String SYMBOL_COLON_EQUAL = ":";
    public static final String SYMBOL_PERCENT = "%";

    public static final String KEY_ANSWERED_CORRECTLY = "answeredCorrectly";
    public static final String KEY_USER = "user";

    public static final String REQUEST_MAPPING_ANSWER = "/answer";
    public static final String REQUEST_MAPPING_ALL = "/all";
    public static final String REQUEST_MAPPING_CORRECTNESS = "/correctness";
    public static final String REQUEST_MAPPING_USER = "/user";
    public static final String REQUEST_MAPPING_CORRECTNESS_USER = "/correctnessUser";

    public static final String REQUEST_PARAM_ANSWERED_CORRECTLY = "answeredCorrectly";
    public static final String REQUEST_PARAM_USER_ID = "userId";
}
