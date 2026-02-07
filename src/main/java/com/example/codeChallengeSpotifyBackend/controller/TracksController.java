package com.example.codeChallengeSpotifyBackend.controller;

import com.example.codeChallengeSpotifyBackend.dtos.TracksDTO;
import com.example.codeChallengeSpotifyBackend.service.TracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/codechallenge")
public class TracksController {

    @Autowired
    private TracksService tracksService;

    @PostMapping("/createTrack")
    public ResponseEntity<TracksDTO> createTrack(
            @RequestParam String isrc,
            @RequestHeader("Authorization") String token
    ) {
        TracksDTO trackDTO = tracksService.createTrack(isrc, token);
        return ResponseEntity.ok(trackDTO);
    }

}
