package com.aua.movie.controller;

import com.aua.movie.dto.ProfileDto;
import com.aua.movie.service.ProfileService;
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
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        List<ProfileDto> body = profileService.findAll();
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id) {
        ProfileDto body = profileService.getProfile(id);
        return ResponseEntity.ok(body);
    }

    @PostMapping
    public ResponseEntity<ProfileDto> registerProfile(@RequestBody @Valid ProfileDto profileDto) {
        ProfileDto body = profileService.registerProfile(profileDto);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody @Valid ProfileDto profileDto, @PathVariable Long id) {
        ProfileDto body = profileService.updateProfile(profileDto, id);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }
}
