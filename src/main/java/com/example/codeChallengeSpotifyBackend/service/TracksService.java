package com.example.codeChallengeSpotifyBackend.service;

import com.example.codeChallengeSpotifyBackend.dtos.TracksDTO;
import com.example.codeChallengeSpotifyBackend.exception.IsrcNotFoundException;
import com.example.codeChallengeSpotifyBackend.mapper.TracksMapper;
import com.example.codeChallengeSpotifyBackend.mapper.spotify.SpotifyTrackJsonParser;
import com.example.codeChallengeSpotifyBackend.model.Albums;
import com.example.codeChallengeSpotifyBackend.model.Tracks;
import com.example.codeChallengeSpotifyBackend.repository.TracksRepository;
import org.json.JSONObject;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
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
        return track.map(tracksMapper::toDto).orElseThrow(() -> new IsrcNotFoundException("ISRC not found!"));
    }

    public Resource getCover(String isrc) throws MalformedURLException {
        Tracks track = tracksRepository.findById(isrc).orElseThrow(() -> new IsrcNotFoundException("ISRC not found!"));
        URL url = new URL(track.getAlbum().getCoverPath());

        return new UrlResource(url);
    }

}
