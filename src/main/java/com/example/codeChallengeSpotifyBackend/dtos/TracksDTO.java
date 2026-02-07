package com.example.codeChallengeSpotifyBackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TracksDTO {
    private String name;
    private String artistName;
    private boolean isExplicit;
    private int playbackSeconds;
    private String isrc;
    private AlbumsDTO album;

    public TracksDTO(String name, String artistName, boolean isExplicit, int playbackSeconds, String isrc, AlbumsDTO album) {
        this.name = name;
        this.artistName = artistName;
        this.isExplicit = isExplicit;
        this.playbackSeconds = playbackSeconds;
        this.isrc = isrc;
        this.album = album;
    }
}
