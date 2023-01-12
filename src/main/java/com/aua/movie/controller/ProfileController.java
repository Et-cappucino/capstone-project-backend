package com.aua.movie.controller;

import com.aua.movie.model.Profile;
import com.aua.movie.service.ProfileService;
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
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public List<Profile> getAllProfiles() {
        return profileService.findAll();
    }

    @GetMapping("/{id}")
    public Profile getProfile(@PathVariable Long id) {
        return profileService.getProfile(id);
    }

    @PostMapping
    public Profile registerProfile(@RequestBody Profile profile) {
        return profileService.registerProfile(profile);
    }

    @PutMapping
    public void updateProfile(@RequestBody Profile profile) {
        profileService.updateProfile(profile);
    }

    @DeleteMapping("/{id}")
    public void removeProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
    }
}
