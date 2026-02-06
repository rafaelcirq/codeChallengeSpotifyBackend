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
    public String isrc;

    public String name;

    public String artistName;

    public Boolean isExplicit;

    public int playbackSeconds;

    @ManyToOne
    @JoinColumn(name = "album_id")
    public Albums album;

}
