package com.example.codeChallengeSpotifyBackend.exception.albums;

public class AlbumNotFoundException extends RuntimeException  {
    public AlbumNotFoundException() {
        super("Album not found.");
    }
}
