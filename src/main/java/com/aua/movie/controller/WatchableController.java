package com.aua.movie.controller;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.service.WatchableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/watchables")
public class WatchableController {

    private final WatchableService watchableService;

    @GetMapping
    public ResponseEntity<List<WatchableDto>> getAllWatchables() {
        List<WatchableDto> body = watchableService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WatchableDto> getWatchable(@PathVariable Long id) {
        WatchableDto body = watchableService.getWatchable(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<WatchableDto> registerWatchable(@RequestBody @Valid WatchableDto watchableDto) {
        WatchableDto body = watchableService.registerWatchable(watchableDto);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WatchableDto> updateWatchable(@RequestBody @Valid WatchableDto watchableDto, @PathVariable Long id) {
        WatchableDto body = watchableService.updateWatchable(watchableDto, id);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeWatchable(@PathVariable Long id) {
        watchableService.deleteWatchable(id);
        return ResponseEntity.ok().build();
    }
}
