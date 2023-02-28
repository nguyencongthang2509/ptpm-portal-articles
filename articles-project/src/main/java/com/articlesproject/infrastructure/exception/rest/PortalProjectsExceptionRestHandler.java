package com.articlesproject.infrastructure.exception.rest;

import com.articlesproject.infrastructure.exception.PortalProjectsExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class PortalProjectsExceptionRestHandler<Z extends Exception>
        extends PortalProjectsExceptionHandler<ResponseEntity<?>,Z> {

    @Override
    protected ResponseEntity<?> wrap(Z ex) {
        return new ResponseEntity<>(wrapApi(ex), HttpStatus.BAD_REQUEST);
    }

    protected abstract Object wrapApi(Z ex);
}