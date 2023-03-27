package com.articlesproject.infrastructure.exception.rest;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestControllerAdvice
public final class RestExceptionHandler extends
        ArticleProjectExceptionRestHandler<ConstraintViolationException> {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleExption(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });

        return errorMap;
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public Map<String , String> handleExption(UnexpectedTypeException exception){
        Map<String, String> errorMap = new HashMap<>();
        exception.getLocalizedMessage();
        Logger.getGlobal().info("this is an error from size");
        return errorMap;

    }

    @ExceptionHandler(RestApiException.class)
    public ResponseEntity<?> handlerException(RestApiException restApiException) {
        ApiError apiError = new ApiError(restApiException.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected Object wrapApi(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violations = ex.getConstraintViolations();
        List<ErrorModel> errors = violations.stream()
                .map(violation ->
                        new ErrorModel(getPropertyName(violation.getPropertyPath()), violation.getMessage()))
                .collect(Collectors.toList());
        return errors;
    }

    private String getPropertyName(Path path) {
        String pathStr = path.toString();
        String[] comps = pathStr.split("\\.");
        if (comps.length > 0) {
            return comps[comps.length - 1];
        } else {
            return pathStr;
        }
    }
}
