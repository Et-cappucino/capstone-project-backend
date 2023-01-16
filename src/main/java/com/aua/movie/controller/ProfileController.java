package com.aua.movie.controller;

import com.aua.movie.model.Profile;
import com.aua.movie.service.ProfileService;
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
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<Profile>> getAllProfiles() {
        List<Profile> body = profileService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        Profile body = profileService.getProfile(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<Profile> registerProfile(@RequestBody Profile profile) {
        Profile body = profileService.registerProfile(profile);
        return ResponseEntity.ok(body);
    }

    @PutMapping
    public ResponseEntity<Void> updateProfile(@RequestBody Profile profile) {
        profileService.updateProfile(profile);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }
}
