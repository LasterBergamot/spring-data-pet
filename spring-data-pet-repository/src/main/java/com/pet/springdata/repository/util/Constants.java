package com.pet.springdata.repository.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String SYMBOL_GREATER_THAN_OR_EQUAL_TO = ">";
    public static final String SYMBOL_LESS_THAN_OR_EQUAL_TO = "<";
    public static final String SYMBOL_COLON_EQUAL = ":";
    public static final String SYMBOL_PERCENT = "%";

    public static final String TABLE_NAME_ANSWERS = "answers";
    public static final String TABLE_NAME_USERS = "users";

    public static final String JOIN_COLUMN_USER_ID = "user_id";
    public static final String JOIN_COLUMN_TRIVIA_ID = "trivia_id";

    public static final String COLUMN_NAME_SELECTED_ANSWER = "selected_answer";
    public static final String COLUMN_NAME_ANSWERED_CORRECTLY = "answered_correctly";
    public static final String COLUMN_NAME_FIRST_NAME = "first_name";
    public static final String COLUMN_NAME_MIDDLE_NAME = "middle_name";
    public static final String COLUMN_NAME_LAST_NAME = "last_name";
    public static final String COLUMN_NAME_PHONE_NUMBER = "phone_number";

    public static final String CLASS_PATH_RESOURCE_EHCACHE_XML = "ehcache.xml";

    public static final String ATTRIBUTE_OVERRIDE_NAME_FIRST_NAME = "firstName";
    public static final String ATTRIBUTE_OVERRIDE_NAME_MIDDLE_NAME = "middleName";
    public static final String ATTRIBUTE_OVERRIDE_NAME_LAST_NAME = "lastName";

    public static final String COLLECTION_TABLE_NAME_PHONE_NUMBERS = "phone_numbers";
}
