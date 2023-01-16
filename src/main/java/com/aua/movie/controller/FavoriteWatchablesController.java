package com.aua.movie.controller;

import com.aua.movie.model.Watchable;
import com.aua.movie.service.FavoriteWatchablesService;
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
@RequestMapping("/api/favorites")
public class FavoriteWatchablesController {

    private final FavoriteWatchablesService favoriteWatchablesService;

    @PostMapping("/{profileId}/{watchableId}")
    public ResponseEntity<Void> addToFavorites(@PathVariable Long watchableId, @PathVariable Long profileId) {
        favoriteWatchablesService.addToFavorites(watchableId, profileId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{profileId}/{watchableId}")
    public ResponseEntity<Void> removeFromFavorites(@PathVariable Long watchableId, @PathVariable Long profileId) {
        favoriteWatchablesService.removeFromFavorites(watchableId, profileId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<List<Watchable>> getProfileFavorites(@PathVariable Long profileId) {
        List<Watchable> body = favoriteWatchablesService.getProfileFavorites(profileId);
        return ResponseEntity.ok(body);
    }
}
