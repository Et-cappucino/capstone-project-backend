package com.aua.movie.controller;

import com.aua.movie.model.Actor;
import com.aua.movie.service.ActorService;
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
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<List<Actor>> getAllActors() {
        List<Actor> body = actorService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Actor> getActor(@PathVariable Long id) {
        Actor body = actorService.getActor(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Actor> registerActor(@RequestBody Actor actor) {
        Actor body = actorService.registerActor(actor);
        return ResponseEntity.ok(body);
    }

    @PutMapping
    public ResponseEntity<Void> updateActor(@RequestBody Actor actor) {
        actorService.updateActor(actor);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok().build();
    }
}
