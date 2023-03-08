package com.articlesproject.infrastructure.constant;

public enum Message {

    SUCCESS("Success"),

    //    ERROR_UNKNOWN("Error Unknown"),
    ERROR_UNKNOWN("Error Unknown"),

    ARTICLE_NOT_EXIT("Error Article Not Exit");
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
