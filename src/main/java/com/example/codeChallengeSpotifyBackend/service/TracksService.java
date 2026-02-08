package com.example.codeChallengeSpotifyBackend.service;

import com.example.codeChallengeSpotifyBackend.dtos.AlbumsDTO;
import com.example.codeChallengeSpotifyBackend.dtos.TracksDTO;
import com.example.codeChallengeSpotifyBackend.exception.tracks.ExistingIsrcException;
import com.example.codeChallengeSpotifyBackend.exception.tracks.IsrcNotFoundException;
import com.example.codeChallengeSpotifyBackend.mapper.AlbumsMapper;
import com.example.codeChallengeSpotifyBackend.mapper.TracksMapper;
import com.example.codeChallengeSpotifyBackend.model.Albums;
import com.example.codeChallengeSpotifyBackend.model.Tracks;
import com.example.codeChallengeSpotifyBackend.repository.TracksRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TracksService {

    private final SpotifyClientService spotifyClientService;

    private final TracksRepository tracksRepository;

    private final TracksMapper tracksMapper;

    private final AlbumsService albumService;

    private final AlbumsMapper albumsMapper;

    public TracksService(SpotifyClientService spotifyClientService, TracksRepository tracksRepository, TracksMapper tracksMapper, AlbumsService albumService, AlbumsMapper albumsMapper) {
        this.spotifyClientService = spotifyClientService;
        this.tracksRepository = tracksRepository;
        this.tracksMapper = tracksMapper;
        this.albumService = albumService;
        this.albumsMapper = albumsMapper;
    }

    public TracksDTO createTrack(String isrc, String token) {
        if (tracksRepository.findById(isrc).isPresent())
            throw new ExistingIsrcException();
        String spotifyApiTrackResponse = spotifyClientService.searchTrackByIsrc(isrc, token);
        TracksDTO trackDto = tracksMapper.fromSpotifyApiResponse(spotifyApiTrackResponse);
        String spotifyApiAlbumResponse = spotifyClientService.getAlbumById(trackDto.getAlbumId(), token);
        AlbumsDTO albumDTO = albumsMapper.fromSpotifyApiResponse(spotifyApiAlbumResponse);
        Tracks track = tracksMapper.toEntity(trackDto);
        Albums album = albumService
                .findById(albumDTO.getId())
                .orElseGet(() -> albumService.save(albumsMapper.toEntity(albumDTO)));
        track.setAlbum(album);
        return tracksMapper.toDto(tracksRepository.save(track));
    }

    public TracksDTO getTrackMetadata(String isrc) {
        Optional<Tracks> track = tracksRepository.findById(isrc);
        return track.map(tracksMapper::toDto)
                .orElseThrow(IsrcNotFoundException::new);
    }

    public Resource getCover(String isrc) {
        Tracks track = tracksRepository.findById(isrc)
                .orElseThrow(IsrcNotFoundException::new);
        return albumService.getCoverImage(track.getAlbum().getCoverPath());
    }

}
