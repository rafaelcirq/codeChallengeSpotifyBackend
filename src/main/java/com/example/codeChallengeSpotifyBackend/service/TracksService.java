package com.example.codeChallengeSpotifyBackend.service;

import com.example.codeChallengeSpotifyBackend.model.Albums;
import com.example.codeChallengeSpotifyBackend.model.Tracks;
import com.example.codeChallengeSpotifyBackend.repository.AlbumsRepository;
import com.example.codeChallengeSpotifyBackend.repository.TracksRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TracksService {

    @Autowired
    private SpotifyClientService spotifyClientService;

    @Autowired
    private TracksRepository tracksRepository;

    @Autowired
    private AlbumsRepository albumsRepository;

    public Tracks createTrack(String isrc, String token) {
        String spotifyApiResponse = spotifyClientService.searchTrackByIsrc(isrc, token);
        Tracks track = getTrackFromApiResponse(isrc, spotifyApiResponse);

        return tracksRepository.save(track);
    }

    private Tracks getTrackFromApiResponse(String isrc, String spotifyApiResponse) {
        JSONObject json = new JSONObject(spotifyApiResponse);

        JSONObject track = json.getJSONObject("tracks")
                .getJSONArray("items")
                .getJSONObject(0);

        String trackName = track.getString("name");
        String artistName = track.getJSONArray("artists")
                .getJSONObject(0)
                .getString("name");
        boolean isExplicit = track.getBoolean("explicit");
        int playbackSeconds = track.getInt("duration_ms") / 1000;

        Tracks tracks = new Tracks();
        tracks.setName(trackName);
        tracks.setArtistName(artistName);
        tracks.setIsExplicit(isExplicit);
        tracks.setPlaybackSeconds(playbackSeconds);
        tracks.setIsrc(isrc);
        tracks.setAlbum(getAlbumFromTrackJson(track));

        return tracks;
    }

    private Albums getAlbumFromTrackJson(JSONObject track) {
        JSONObject albumJson = track.getJSONObject("album");
        String albumName = albumJson.getString("name");
        String albumId = albumJson.getString("id");

        JSONArray images = albumJson.getJSONArray("images");
        String imageUrl = images.getJSONObject(0).getString("url");

        Albums album = new Albums();
        album.setName(albumName);
        album.setId(albumId);
        album.setCoverPath(imageUrl);

        return albumsRepository.save(album);
    }

}
