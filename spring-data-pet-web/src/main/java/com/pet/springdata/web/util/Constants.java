package com.pet.springdata.web.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String REQUEST_MAPPING_ANSWER = "/answer";
    public static final String REQUEST_MAPPING_ALL = "/all";
    public static final String REQUEST_MAPPING_CORRECTNESS = "/correctness";
    public static final String REQUEST_MAPPING_USER = "/user";
    public static final String REQUEST_MAPPING_SAVE_USER = "/saveUser";
    public static final String REQUEST_MAPPING_CORRECTNESS_USER = "/correctnessUser";
    public static final String REQUEST_MAPPING_TRIVIA = "/trivia";
    public static final String REQUEST_MAPPING_GET_TRIVIA = "/getTrivia";
    public static final String REQUEST_MAPPING_SAVE_TRIVIA = "/saveTrivia";
    public static final String REQUEST_MAPPING_DETAILS = "/details";
    public static final String REQUEST_MAPPING_CACHE = "/cache";
    public static final String REQUEST_MAPPING_FIRST_LEVEL = "/firstLevel";

    public static final String REQUEST_PARAM_ANSWERED_CORRECTLY = "answeredCorrectly";
    public static final String REQUEST_PARAM_USER_ID = "userId";
    public static final String REQUEST_PARAM_NUMBER_OF_TRIVIA = "numberOfTrivia";
    public static final String REQUEST_PARAM_CATEGORY = "category";
    public static final String REQUEST_PARAM_TYPE = "type";
    public static final String REQUEST_PARAM_DIFFICULTY = "difficulty";
    public static final String REQUEST_PARAM_FIRST_NAME = "firstName";
    public static final String REQUEST_PARAM_MIDDLE_NAME = "middleName";
    public static final String REQUEST_PARAM_LAST_NAME = "lastName";
    public static final String REQUEST_PARAM_PHONE_NUMBER = "phoneNumber";


    public static final String KEY_ENTITY_NAME = "entityName";
    public static final String KEY_HIT_COUNT = "hitCount";
    public static final String KEY_MISS_COUNT = "missCount";
    public static final String KEY_PUT_COUNT = "putCount";
    public static final String KEY_REGION_NAME = "regionName";
    public static final String KEY_DELETE_COUNT = "deleteCount";
    public static final String KEY_INSERT_COUNT = "insertCount";
    public static final String KEY_UPDATE_COUNT = "updateCount";
    public static final String KEY_LOAD_COUNT = "loadCount";
    public static final String KEY_FETCH_COUNT = "fetchCount";
    public static final String REQUEST_MAPPING_SECOND_LEVEL = "/secondLevel";
    public static final String KEY_SECOND_LEVEL_CACHE_HIT_COUNT = "secondLevelCacheHitCount";
    public static final String KEY_SECOND_LEVEL_CACHE_MISS_COUNT = "secondLevelCacheMissCount";
    public static final String KEY_SECOND_LEVEL_CACHE_PUT_COUNT = "secondLevelCachePutCount";
}
