package com.aua.movie.controller;

import com.aua.movie.model.Watchable;
import com.aua.movie.service.WatchableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/watchables")
public class WatchableController {

    private final WatchableService watchableService;

    @GetMapping
    public ResponseEntity<List<Watchable>> getAllWatchables() {
        List<Watchable> body = watchableService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Watchable> getWatchable(@PathVariable Long id) {
        Watchable body = watchableService.getWatchable(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Watchable> registerWatchable(@RequestBody Watchable watchable) {
        Watchable body = watchableService.registerWatchable(watchable);
        return ResponseEntity.ok(body);
    }

    @PutMapping
    public ResponseEntity<Void> updateWatchable(@RequestBody Watchable watchable) {
        watchableService.updateWatchable(watchable);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeWatchable(@PathVariable Long id) {
        watchableService.deleteWatchable(id);
        return ResponseEntity.ok().build();
    }
}
