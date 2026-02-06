package com.example.codeChallengeSpotifyBackend.repository;

import com.example.codeChallengeSpotifyBackend.model.Tracks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TracksRepository extends JpaRepository<Tracks, String> {
}
