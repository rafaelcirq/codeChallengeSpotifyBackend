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
    private String albumId;
    private String albumName;

    public TracksDTO(String name,
                     String artistName,
                     boolean isExplicit,
                     int playbackSeconds,
                     String isrc,
                     String albumId,
                     String albumName) {
        this.name = name;
        this.artistName = artistName;
        this.isExplicit = isExplicit;
        this.playbackSeconds = playbackSeconds;
        this.isrc = isrc;
        this.albumId = albumId;
        this.albumName = albumName;
    }
}
