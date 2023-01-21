package com.aua.movie.controller;

import com.aua.movie.model.Actor;
import com.aua.movie.service.CastService;
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
@RequestMapping("/api/cast")
public class CastController {

    private final CastService castService;

    @PostMapping("/{watchableId}/{actorId}")
    public ResponseEntity<Void> addToCast(@PathVariable Long actorId, @PathVariable Long watchableId) {
        castService.addToCast(actorId, watchableId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{watchableId}/{actorId}")
    public ResponseEntity<Void> removeFromCast(@PathVariable Long actorId, @PathVariable Long watchableId) {
        castService.removeFromCast(actorId, watchableId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{actorId}")
    public ResponseEntity<List<Actor>> getWatchableCast(@PathVariable Long actorId) {
        List<Actor> body = castService.getWatchableCast(actorId);
        return ResponseEntity.ok(body);
    }
}
