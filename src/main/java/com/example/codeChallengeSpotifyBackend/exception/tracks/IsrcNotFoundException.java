package com.example.codeChallengeSpotifyBackend.exception.tracks;

public class IsrcNotFoundException extends RuntimeException {
    public IsrcNotFoundException() {
        super("ISRC not found.");
    }
}
