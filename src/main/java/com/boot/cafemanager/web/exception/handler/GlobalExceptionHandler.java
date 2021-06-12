package com.boot.cafemanager.web.exception.handler;

import com.boot.cafemanager.exception.ResourceNotFoundException;
import com.boot.cafemanager.exception.UserAlreadyExistsException;
import com.boot.cafemanager.exception.ValidationException;
import com.boot.cafemanager.web.rest.model.error.ApiError;
import com.boot.cafemanager.web.rest.model.error.Embedded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> defaultErrorHandler(HttpServletRequest req, Exception e) {
        return handleException(req, e, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<?> handleMissingServletRequestPartException(HttpServletRequest req, MissingServletRequestPartException e) {
        return handleException(req, e, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(HttpServletRequest req, ValidationException e) {
        logError(req, e);
        return handleException(req, e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException(HttpServletRequest req, UserAlreadyExistsException e) {
        logError(req, e);
        return handleException(req, e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(HttpServletRequest req, ResourceNotFoundException e) {
        logError(req, e);
        return handleException(req, e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException e) {
        logError(req, e);
        return handleException(req, e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> methodArgumentNotValidExceptionExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .collect(Collectors.toList());

        if (errors.size() == 1) {
            return createErrorResponse(errors.get(0), HttpStatus.BAD_REQUEST);
        } else {
            return createErrorResponse("Bad Request", errors, HttpStatus.BAD_REQUEST);
        }
    }

    private void logError(HttpServletRequest req, Exception e) {
        logger.warn(e.getMessage());
        logger.warn("RequestURI {}", req.getRequestURI());
    }

    private ResponseEntity<?> handleException(HttpServletRequest req, Exception e, HttpStatus status) {
        ApiError apiError = new ApiError(e.getMessage());
        return ResponseEntity.status(status).body(apiError);

    }

    private ResponseEntity<ApiError> createErrorResponse(String message, HttpStatus status) {
        ApiError apiError = new ApiError(message);
        return ResponseEntity.status(status).body(apiError);
    }

    private ResponseEntity<ApiError> createErrorResponse(String message, List<String> embeddedErrors, HttpStatus status) {
        ApiError apiError = new ApiError(message, Embedded.from(embeddedErrors));
        return ResponseEntity.status(status).body(apiError);
    }
}
