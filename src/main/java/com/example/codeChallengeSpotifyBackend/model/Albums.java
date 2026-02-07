package com.example.codeChallengeSpotifyBackend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Albums {

    @Id
    private String id;

    private String name;

    private String coverPath;

    @OneToMany(mappedBy = "album")
    private List<Tracks> tracks;
}
