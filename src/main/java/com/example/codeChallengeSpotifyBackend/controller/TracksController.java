package com.example.codeChallengeSpotifyBackend.controller;

import com.example.codeChallengeSpotifyBackend.dtos.TracksDTO;
import com.example.codeChallengeSpotifyBackend.service.TracksService;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/codechallenge")
public class TracksController {

    private final TracksService tracksService;

    public TracksController(TracksService tracksService) {
        this.tracksService = tracksService;
    }

    @PostMapping("/createTrack")
    public ResponseEntity<TracksDTO> createTrack(
            @RequestParam String isrc,
            @RequestHeader("Authorization") String token
    ) {
        TracksDTO trackDTO = tracksService.createTrack(isrc, token);
        return ResponseEntity.ok(trackDTO);
    }

    @GetMapping("/getTrackMetadata")
    public ResponseEntity<TracksDTO> getTrackMetaData(
            @RequestParam String isrc
    ) {
        return ResponseEntity.ok(tracksService.getTrackMetadata(isrc));
    }

    @GetMapping("/getCover")
    public ResponseEntity<Resource> getCover(
            @RequestParam String isrc
    ) {
        Resource image = tracksService.getCover(isrc);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image);
    }

}
