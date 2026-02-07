package com.example.codeChallengeSpotifyBackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Tracks {

    @Id
    private String isrc;

    private String name;

    private String artistName;

    private Boolean isExplicit;

    private int playbackSeconds;

    @ManyToOne
    @JoinColumn(name = "album_id")
    private Albums album;

}
