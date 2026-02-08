package com.example.codeChallengeSpotifyBackend.service;

import com.example.codeChallengeSpotifyBackend.exception.spotify.SpotifyInvalidTokenException;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class SpotifyClientService {

    private static final String BASE_URL = "https://api.spotify.com/v1";

    public String searchTrackByIsrc(String isrc, String token) {
        HttpURLConnection con = null;

        try {
            String urlString = BASE_URL + "/search?q=isrc:" + isrc + "&type=track";
            URL url = new URL(urlString);

            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + token);
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);

            int status = con.getResponseCode();

            if (status == HttpURLConnection.HTTP_UNAUTHORIZED) {
                throw new SpotifyInvalidTokenException();
            }

            if (status >= 400) {
                throw new RuntimeException("Spotify API error. HTTP status: " + status);
            }

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream())
            )) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
                return content.toString();
            }

        } catch (SpotifyInvalidTokenException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error calling Spotify API", e);
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }
}