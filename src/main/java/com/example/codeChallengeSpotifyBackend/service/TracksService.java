package com.example.codeChallengeSpotifyBackend.service;

import com.example.codeChallengeSpotifyBackend.dtos.TracksDTO;
import com.example.codeChallengeSpotifyBackend.exception.albums.AlbumCoverNotFoundException;
import com.example.codeChallengeSpotifyBackend.exception.tracks.ExistingIsrcException;
import com.example.codeChallengeSpotifyBackend.exception.tracks.IsrcNotFoundException;
import com.example.codeChallengeSpotifyBackend.mapper.TracksMapper;
import com.example.codeChallengeSpotifyBackend.mapper.spotify.SpotifyTrackJsonParser;
import com.example.codeChallengeSpotifyBackend.model.Albums;
import com.example.codeChallengeSpotifyBackend.model.Tracks;
import com.example.codeChallengeSpotifyBackend.repository.TracksRepository;
import org.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TracksService {

    private final SpotifyClientService spotifyClientService;

    private final TracksRepository tracksRepository;

    private final TracksMapper tracksMapper;

    private final AlbumsService albumService;

    private final SpotifyTrackJsonParser spotifyTrackJsonParser;

    public TracksService(SpotifyClientService spotifyClientService, TracksRepository tracksRepository, TracksMapper tracksMapper, AlbumsService albumService, SpotifyTrackJsonParser spotifyTrackJsonParser) {
        this.spotifyClientService = spotifyClientService;
        this.tracksRepository = tracksRepository;
        this.tracksMapper = tracksMapper;
        this.albumService = albumService;
        this.spotifyTrackJsonParser = spotifyTrackJsonParser;
    }

    public TracksDTO createTrack(String isrc, String token) {
        if (tracksRepository.findById(isrc).isPresent())
            throw new ExistingIsrcException();

        String spotifyApiResponse = spotifyClientService.searchTrackByIsrc(isrc, token);
        JSONObject trackJson = spotifyTrackJsonParser.extractTrackJson(spotifyApiResponse);
        TracksDTO dto = tracksMapper.fromSpotifyJson(trackJson);
        Tracks track = tracksMapper.toEntity(dto);
        Albums album = albumService
                .findById(track.getAlbum().getId())
                .orElseGet(() -> albumService.save(track.getAlbum()));
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
