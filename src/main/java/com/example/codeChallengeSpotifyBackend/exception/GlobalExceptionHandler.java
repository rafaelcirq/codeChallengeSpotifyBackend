package com.example.codeChallengeSpotifyBackend.exception;

import com.example.codeChallengeSpotifyBackend.exception.albums.AlbumCoverNotFoundException;
import com.example.codeChallengeSpotifyBackend.exception.albums.AlbumNotFoundException;
import com.example.codeChallengeSpotifyBackend.exception.albums.ImageNotSavedException;
import com.example.codeChallengeSpotifyBackend.exception.spotify.SpotifyInvalidTokenException;
import com.example.codeChallengeSpotifyBackend.exception.tracks.ExistingIsrcException;
import com.example.codeChallengeSpotifyBackend.exception.tracks.InvalidIsrcException;
import com.example.codeChallengeSpotifyBackend.exception.tracks.IsrcNotFoundException;
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

    @ExceptionHandler(InvalidIsrcException.class)
    public ResponseEntity<ErrorResponse> handleInvalidIsrcException(
            InvalidIsrcException e
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage(), 404, Instant.now()));
    }

    @ExceptionHandler(ExistingIsrcException.class)
    public ResponseEntity<ErrorResponse> handleExistingIsrcException(
            ExistingIsrcException e
    ) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage(), 409, Instant.now()));
    }

    @ExceptionHandler(ImageNotSavedException.class)
    public ResponseEntity<ErrorResponse> handleImageNotSavedException(
            ImageNotSavedException e
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(e.getMessage(), 500, Instant.now()));
    }

    @ExceptionHandler(AlbumNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAlbumNotFoundException(
            AlbumNotFoundException e
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(e.getMessage(), 404, Instant.now()));
    }

    @ExceptionHandler(AlbumCoverNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAlbumCoverNotFoundException(
            AlbumCoverNotFoundException e
    ) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(e.getMessage(), 404, Instant.now()));
    }
}