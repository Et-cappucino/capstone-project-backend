package com.aua.movie.controller;

import com.aua.movie.dto.ActorDto;
import com.aua.movie.service.ActorService;
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
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    @GetMapping
    public ResponseEntity<List<ActorDto>> getAllActors() {
        List<ActorDto> body = actorService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> getActor(@PathVariable Long id) {
        ActorDto body = actorService.getActor(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<ActorDto> registerActor(@RequestBody @Valid ActorDto actorDto) {
        ActorDto body = actorService.registerActor(actorDto);
        return ResponseEntity.ok(body);
    }

    @PutMapping
    public ResponseEntity<Void> updateActor(@RequestBody @Valid ActorDto actorDto) {
        actorService.updateActor(actorDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok().build();
    }
}
