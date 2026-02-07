package com.example.codeChallengeSpotifyBackend.mapper.spotify;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SpotifyTrackJsonParser {
    public JSONObject extractTrackJson(String response) {
        return new JSONObject(response)
                .getJSONObject("tracks")
                .getJSONArray("items")
                .getJSONObject(0);
    }
}
