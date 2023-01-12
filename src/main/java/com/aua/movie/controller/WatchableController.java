package com.aua.movie.controller;

import com.aua.movie.model.Watchable;
import com.aua.movie.service.WatchableService;
import lombok.RequiredArgsConstructor;
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
    public List<Watchable> getAllWatchables() {
        return watchableService.findAll();
    }

    @GetMapping("/{id}")
    public Watchable getWatchable(@PathVariable Long id) {
        return watchableService.getWatchable(id);
    }

    @PostMapping
    public Watchable registerWatchable(@RequestBody Watchable watchable) {
        return watchableService.registerWatchable(watchable);
    }

    @PutMapping
    public void updateWatchable(@RequestBody Watchable watchable) {
        watchableService.updateWatchable(watchable);
    }

    @DeleteMapping("/{id}")
    public void removeWatchable(@PathVariable Long id) {
        watchableService.deleteWatchable(id);
    }
}
