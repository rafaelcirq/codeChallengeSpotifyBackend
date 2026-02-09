package com.example.codeChallengeSpotifyBackend.exception.spotify;

public class SpotifyInvalidTokenException extends RuntimeException {
    public SpotifyInvalidTokenException() {
        super("Spotify access token is invalid or expired.");
    }
}
