package com.example.codeChallengeSpotifyBackend.repository;

import com.example.codeChallengeSpotifyBackend.model.Albums;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumsRepository extends JpaRepository<Albums, String> {
}
