package com.example.codeChallengeSpotifyBackend.exception.albums;

public class AlbumCoverNotFoundException extends RuntimeException {
    public AlbumCoverNotFoundException() {
        super("Cover not found.");
    }
}
