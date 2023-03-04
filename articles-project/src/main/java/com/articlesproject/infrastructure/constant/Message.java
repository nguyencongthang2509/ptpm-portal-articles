package com.articlesproject.infrastructure.constant;

import com.articlesproject.util.PropertiesReader;

public enum Message {

    SUCCESS("Success"),

    //    ERROR_UNKNOWN("Error Unknown"),
    ERROR_UNKNOWN("Error Unknown"),

    USER_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.USER_NOT_EXIST)),
    ALBUM_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.ALBUM_NOT_EXIST)),
    ARTICLE_ALBUM_NOT_EXIST(PropertiesReader.getProperty(PropertyKeys.ARTICLE_ALBUM_NOT_EXIST));

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
