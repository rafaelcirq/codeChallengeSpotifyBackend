package com.example.codeChallengeSpotifyBackend.mapper;

import com.example.codeChallengeSpotifyBackend.dtos.TracksDTO;
import com.example.codeChallengeSpotifyBackend.model.Tracks;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class TracksMapper {

    public TracksDTO fromSpotifyApiResponse(String apiResponse) {
        JSONObject trackJson = new JSONObject(apiResponse)
                .getJSONObject("tracks")
                .getJSONArray("items")
                .getJSONObject(0);

        return new TracksDTO(
                trackJson.getString("name"),
                trackJson.getJSONArray("artists").getJSONObject(0).getString("name"),
                trackJson.getBoolean("explicit"), trackJson.getInt("duration_ms") / 1000,
                trackJson.getJSONObject("external_ids").getString("isrc"),
                trackJson.getJSONObject("album").getString("id"),
                trackJson.getJSONObject("album").getString("name"));
    }

    public Tracks toEntity(TracksDTO dto) {
        Tracks track = new Tracks();
        track.setName(dto.getName());
        track.setArtistName(dto.getArtistName());
        track.setIsExplicit(dto.isExplicit());
        track.setPlaybackSeconds(dto.getPlaybackSeconds());
        track.setIsrc(dto.getIsrc());
        return track;
    }

    public TracksDTO toDto(Tracks track) {
        return new TracksDTO(
                track.getName(),
                track.getArtistName(),
                track.getIsExplicit(),
                track.getPlaybackSeconds(),
                track.getIsrc(),
                track.getAlbum().getId(),
                track.getAlbum().getName()
        );
    }
}
