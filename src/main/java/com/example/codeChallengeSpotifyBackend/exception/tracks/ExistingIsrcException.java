package com.example.codeChallengeSpotifyBackend.exception.tracks;

public class ExistingIsrcException extends RuntimeException {
    public ExistingIsrcException() {
        super("Informed ISRC already exists.");
    }
}
