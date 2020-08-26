package com.pet.springdata.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String OPEN_TRIVIA_DB_TOKEN_URL = "https://opentdb.com/api_token.php?command=request";
    public static final String OPEN_TRIVIA_DB_API_URL = "https://opentdb.com/api.php?amount=%s&token=%s";

    public static final String EMPTY_STRING = "";
}
