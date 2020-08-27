package com.pet.springdata.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String OPEN_TRIVIA_DB_TOKEN_URL = "https://opentdb.com/api_token.php?command=request";
    public static final String OPEN_TRIVIA_DB_API_URL = "https://opentdb.com/api.php?amount=%s&token=%s";

    public static final String EMPTY_STRING = "";

    public static final int MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST = 50;

    public static final String GREATER_THAN_OR_EQUAL_SYMBOL = ">";
    public static final String LESS_THAN_OR_EQUAL_SYMBOL = "<";
    public static final String COLON_EQUAL_SYMBOL = ":";
    public static final String PERCENT_SYMBOL = "%";
}
