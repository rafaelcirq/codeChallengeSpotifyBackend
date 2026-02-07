package com.example.codeChallengeSpotifyBackend.service;

import com.example.codeChallengeSpotifyBackend.model.Albums;
import com.example.codeChallengeSpotifyBackend.repository.AlbumsRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AlbumsService {

    private final AlbumsRepository albumsRepository;

    public AlbumsService(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    public Albums save(Albums album) {
        return albumsRepository.save(album);
    }

    public Optional<Albums> findById(String id) {
        return albumsRepository.findById(id);
    }

}
