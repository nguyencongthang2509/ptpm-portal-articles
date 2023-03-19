package com.articlesproject.infrastructure.constant;

import com.articlesproject.util.PropertiesReader;

public enum Message {

    SUCCESS("Success"),

    //    ERROR_UNKNOWN("Error Unknown"),
    ERROR_UNKNOWN("Error Unknown"),

    ARTICLE_NOT_EXIT("Error Article Not Exit"),

    USER_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.USER_NOT_EXIST)),
    ALBUM_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.ALBUM_NOT_EXIST)),
    ARTICLE_ALBUM_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.ARTICLE_ALBUM_NOT_EXIST)),
    COMMENT_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.COMMENT_NOT_EXIST)),
    CATEGORY_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.CATEGORY_NOT_EXIST)),
    ARTICLE_ALREADY_EXIST(PropertiesReader.getProperty(PropertyKeys.ARTICLE_ALREADY_EXIST)),
    CATEGORY_CODE_ALREADY_EXIST(PropertiesReader.getProperty(PropertyKeys.CATEGORY_CODE_ALREADY_EXIST)),
    ARTICLE_HASHTAG_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.ARTICLE_HASHTAG_NOT_EXIST)),
    ARTICLE_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.ARTICLE_NOT_EXIST));
//    ERROR_UNKNOWN("Error Unknown"),


//    CHUYEN_NGANH_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.CHUYEN_NGANH_NOT_EXIST)),

    private String message;

    Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
