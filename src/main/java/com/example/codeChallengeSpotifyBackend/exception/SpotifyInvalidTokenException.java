package com.example.codeChallengeSpotifyBackend.exception;

public class SpotifyInvalidTokenException extends RuntimeException {
    public SpotifyInvalidTokenException(String message) {
        super(message);
    }
}
