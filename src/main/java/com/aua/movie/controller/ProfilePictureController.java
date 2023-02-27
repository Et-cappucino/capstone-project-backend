package com.aua.movie.controller;

import com.aua.movie.dto.ProfileDto;
import com.aua.movie.dto.ProfilePictureDto;
import com.aua.movie.service.ProfilePictureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Api(value = "Profile Picture service rest API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/images")
public class ProfilePictureController {

    private final ProfilePictureService profilePictureService;

    @ApiOperation(value = "Upload a Profile picture", response = ProfileDto.class, tags = "profile-picture-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping("/{profileId}")
    public ResponseEntity<ProfilePictureDto> uploadProfilePicture(@RequestParam("image") MultipartFile imageFile, @PathVariable Long profileId) throws IOException {
        ProfilePictureDto body = profilePictureService.addProfilePicture(imageFile, profileId);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get Profile's picture by ID", tags = "profile-picture-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/{id}")
    public ResponseEntity<ProfilePictureDto> getProfilePicture(@PathVariable Long id) {
        ProfilePictureDto body = profilePictureService.getProfilePicture(id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Change Profile's picture by ID", tags = "profile-picture-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PutMapping("/{id}")
    public ResponseEntity<ProfilePictureDto> changeProfilePicture(@RequestParam("image") MultipartFile imageFile, @PathVariable Long id) throws IOException {
        ProfilePictureDto body = profilePictureService.updateProfilePicture(imageFile, id);
        return ResponseEntity.ok(body);
    }
}
