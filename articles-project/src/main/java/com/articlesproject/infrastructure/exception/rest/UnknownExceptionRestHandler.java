package com.articlesproject.infrastructure.exception.rest;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public final class UnknownExceptionRestHandler extends
        ArticleProjectExceptionRestHandler<Exception> {

    @Override
    protected Object wrapApi(Exception ex) {
        return ex.getMessage();
    }
}
