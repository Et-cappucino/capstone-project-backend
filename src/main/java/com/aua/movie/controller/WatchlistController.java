package com.aua.movie.controller;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;

    @PostMapping("/{profileId}/{watchableId}")
    public ResponseEntity<Void> addToWatchlist(@PathVariable Long watchableId, @PathVariable Long profileId) {
        watchlistService.addToWatchlist(watchableId, profileId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{profileId}/{watchableId}")
    public ResponseEntity<Void> removeFromWatchlist(@PathVariable Long watchableId, @PathVariable Long profileId) {
        watchlistService.removeFromWatchlist(watchableId, profileId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<List<WatchableDto>> getProfileWatchlist(@PathVariable Long profileId) {
        List<WatchableDto> body = watchlistService.getProfileWatchlist(profileId);
        return ResponseEntity.ok(body);
    }
}
