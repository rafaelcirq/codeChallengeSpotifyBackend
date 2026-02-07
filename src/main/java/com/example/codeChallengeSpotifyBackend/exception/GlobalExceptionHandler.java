package com.example.codeChallengeSpotifyBackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SpotifyInvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleSpotifyInvalidToken(
            SpotifyInvalidTokenException e
    ) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponse(e.getMessage(), 401, Instant.now()));
    }

    @ExceptionHandler(IsrcNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleIsrcNotFoundException(
            IsrcNotFoundException e
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage(), 404, Instant.now()));
    }
}