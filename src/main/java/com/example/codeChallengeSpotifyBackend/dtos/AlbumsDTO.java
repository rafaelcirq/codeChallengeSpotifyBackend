package com.example.codeChallengeSpotifyBackend.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumsDTO {
    private String id;
    private String name;
    private String coverPath;

    public AlbumsDTO(String id, String name, String coverPath) {
        this.id = id;
        this.name = name;
        this.coverPath = coverPath;
    }
}
