package com.example.codeChallengeSpotifyBackend.exception;

public class IsrcNotFoundException extends RuntimeException {
    public IsrcNotFoundException(String message) {
        super(message);
    }
}
