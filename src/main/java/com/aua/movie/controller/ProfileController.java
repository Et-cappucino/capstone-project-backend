package com.aua.movie.controller;

import com.aua.movie.dto.ProfileDto;
import com.aua.movie.service.ProfileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "Profile service rest API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    @ApiOperation(value = "Get all Profiles with pagination support", tags = "profile-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping
    public ResponseEntity<Page<ProfileDto>> getAllProfiles(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<ProfileDto> body = profileService.findAll(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get a single Profile by ID", tags = "profile-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id) {
        ProfileDto body = profileService.getProfile(id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get a single Profile by email", tags = "profile-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/profile")
    public ResponseEntity<ProfileDto> getProfileByEmail(@RequestParam(value = "email") String email) {
        ProfileDto body = profileService.findProfileByEmail(email);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Create and register a new Profile", response = ProfileDto.class, tags = "profile-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping
    public ResponseEntity<ProfileDto> registerProfile(@RequestBody @Valid ProfileDto profileDto) {
        ProfileDto body = profileService.registerProfile(profileDto);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Update a Profile by ID with a new Profile body", tags = "profile-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDto> updateProfile(@RequestBody @Valid ProfileDto profileDto, @PathVariable Long id) {
        ProfileDto body = profileService.updateProfile(profileDto, id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Delete a Profile by ID", tags = "profile-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Check if Profile's email is confirmed or not", tags = "profile-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/verification/{profileId}")
    public ResponseEntity<Boolean> isProfileEmailConfirmed(@PathVariable Long profileId) {
        Boolean body = profileService.isProfileEnabled(profileId);
        return ResponseEntity.ok(body);
    }
}
