package com.example.codeChallengeSpotifyBackend.exception.albums;

public class ImageNotSavedException extends RuntimeException {
    public ImageNotSavedException() {
        super("Image was not saved.");
    }
}
