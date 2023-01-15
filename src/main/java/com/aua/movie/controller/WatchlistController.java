package com.aua.movie.controller;

import com.aua.movie.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/add-to-watchlist")
public class WatchlistController {

    private final ProfileService profileService;

    @PostMapping("/{profileId}/{watchableId}")
    public ResponseEntity<Void> addToWatchlist(@PathVariable Long watchableId, @PathVariable Long profileId) {
        profileService.addToWatchlist(watchableId, profileId);
        return ResponseEntity.ok().build();
    }
}
