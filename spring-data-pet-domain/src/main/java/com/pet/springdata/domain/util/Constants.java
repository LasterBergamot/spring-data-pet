package com.pet.springdata.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String URL_OPEN_TRIVIA_DB_TOKEN = "https://opentdb.com/api_token.php?command=request";
    public static final String URL_OPEN_TRIVIA_DB_API = "https://opentdb.com/api.php?amount=%s&token=%s";

    public static final String EMPTY_STRING = "";

    public static final int MAXIMUM_NUMBER_OF_TRIVIA_PER_REQUEST = 50;
}
