package com.pet.springdata.web.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String REQUEST_MAPPING_ANSWER = "/answer";
    public static final String REQUEST_MAPPING_ALL = "/all";
    public static final String REQUEST_MAPPING_CORRECTNESS = "/correctness";
    public static final String REQUEST_MAPPING_USER = "/user";
    public static final String REQUEST_MAPPING_CORRECTNESS_USER = "/correctnessUser";
    public static final String REQUEST_MAPPING_TRIVIA = "/trivia";
    public static final String REQUEST_MAPPING_GET_TRIVIA = "/getTrivia";
    public static final String REQUEST_MAPPING_SAVE_TRIVIA = "/saveTrivia";
    public static final String REQUEST_MAPPING_DETAILS = "/details";

    public static final String REQUEST_PARAM_ANSWERED_CORRECTLY = "answeredCorrectly";
    public static final String REQUEST_PARAM_USER_ID = "userId";
    public static final String REQUEST_PARAM_NUMBER_OF_TRIVIA = "numberOfTrivia";
    public static final String REQUEST_PARAM_CATEGORY = "category";
    public static final String REQUEST_PARAM_TYPE = "type";
    public static final String REQUEST_PARAM_DIFFICULTY = "difficulty";
}
