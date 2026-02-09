package com.example.codeChallengeSpotifyBackend.exception.tracks;

public class InvalidIsrcException extends RuntimeException {
    public InvalidIsrcException() {
        super("Invalid ISRC number.");
    }
}
