package com.example.codeChallengeSpotifyBackend.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class SpotifyClientService {

    private final String baseUrl = "https://api.spotify.com/v1";

    public String searchTrackByIsrc(String isrc, String token) {
        try {
            String urlString = baseUrl + "/search?q=isrc:" + isrc + "&type=track";
            URL url = new URL(urlString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Authorization", "Bearer " + token);

            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    status > 299 ? con.getErrorStream() : con.getInputStream()
            ));
            String inputLine;
            StringBuilder content = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            con.disconnect();

            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
