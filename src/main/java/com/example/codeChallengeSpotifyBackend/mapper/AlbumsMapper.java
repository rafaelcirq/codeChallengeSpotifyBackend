package com.example.codeChallengeSpotifyBackend.mapper;

import com.example.codeChallengeSpotifyBackend.dtos.AlbumsDTO;
import com.example.codeChallengeSpotifyBackend.model.Albums;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


@Component
public class AlbumsMapper {

    public AlbumsDTO fromSpotifyApiResponse(String apiResponse) {
        JSONObject albumJson = new JSONObject(apiResponse);
        JSONArray images = albumJson.getJSONArray("images");
        String coverPath = images.getJSONObject(0).getString("url");

        return new AlbumsDTO(
                albumJson.getString("id"),
                albumJson.getString("name"),
                coverPath
        );
    }

    public Albums toEntity(AlbumsDTO dto) {
        Albums album = new Albums();
        album.setId(dto.getId());
        album.setName(dto.getName());
        album.setCoverPath(dto.getCoverPath());
        return album;
    }

    public AlbumsDTO toDto(Albums album) {
        return new AlbumsDTO(
                album.getId(),
                album.getName(),
                album.getCoverPath()
        );
    }
}
