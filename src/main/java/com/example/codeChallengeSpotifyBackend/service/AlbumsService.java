package com.example.codeChallengeSpotifyBackend.service;

import com.example.codeChallengeSpotifyBackend.exception.albums.AlbumCoverNotFoundException;
import com.example.codeChallengeSpotifyBackend.exception.albums.ImageNotSavedException;
import com.example.codeChallengeSpotifyBackend.model.Albums;
import com.example.codeChallengeSpotifyBackend.repository.AlbumsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@Service
public class AlbumsService {

    private final AlbumsRepository albumsRepository;

    @Value("${covers.path}")
    private String coversPath;

    public AlbumsService(AlbumsRepository albumsRepository) {
        this.albumsRepository = albumsRepository;
    }

    public Albums save(Albums album) {
        Path coverPath = saveCoverImage(album.getCoverPath(), album.getId());
        album.setCoverPath(coverPath.toString());
        album = albumsRepository.save(album);
        return album;
    }

    public Optional<Albums> findById(String id) {
        return albumsRepository.findById(id);
    }

    public Path saveCoverImage(String imageUrl, String albumId) {
        try {
            Path folderPath = Paths.get(coversPath);
            if (Files.notExists(folderPath)) {
                Files.createDirectories(folderPath);
            }
            String extension = getCoverImageExtension(imageUrl);
            try (InputStream in = new URL(imageUrl).openStream()) {
                Path coverPath = getCoverPath(albumId, extension);
                Files.copy(in, coverPath, StandardCopyOption.REPLACE_EXISTING);
                return coverPath;
            }
        } catch (Exception e) {
            throw new ImageNotSavedException();
        }
    }

    public Resource getCoverImage(String albumCoverPath) {
        Path coverPath = Paths.get(albumCoverPath);
        if (Files.notExists(coverPath))
            throw new AlbumCoverNotFoundException();
        return new FileSystemResource(coverPath);
    }

    private Path getCoverPath(String albumId, String extension) {
        return Paths.get(coversPath).resolve(albumId + "." + extension);
    }

    private String getCoverImageExtension(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            URLConnection connection = url.openConnection();
            String contentType = connection.getContentType();
            return contentType.split("/")[1];
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException("Image extension could not me extracted!");
        }
    }
}
