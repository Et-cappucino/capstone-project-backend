package com.aua.movie.controller;

import com.aua.movie.model.enums.Genre;
import com.aua.movie.service.FavoriteGenresService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@Api(value = "Favorite genres service rest API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class FavoriteGenresController {

    private final FavoriteGenresService favoriteGenresService;

    @ApiOperation(value = "Add a Genre to the Profile's Favorite Genres by ID", tags = "favorite-genres-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping("/{profileId}/{genre}")
    public ResponseEntity<Void> addToFavoriteGenres(@PathVariable Genre genre, @PathVariable Long profileId) {
        favoriteGenresService.addToFavoriteGenres(genre, profileId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Remove a Genre from the Profile's Favorite Genres by ID", tags = "favorite-genres-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping("/{profileId}/{genre}")
    public ResponseEntity<Void> removeFromFavoriteGenres(@PathVariable Genre genre, @PathVariable Long profileId) {
        favoriteGenresService.removeFromFavoriteGenres(genre, profileId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Get a Profile's Favorite genres by ID", tags = "favorite-genres-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/{profileId}")
    public ResponseEntity<Set<Genre>> getProfileFavoriteGenres(@PathVariable Long profileId) {
        Set<Genre> body = favoriteGenresService.getProfileFavoriteGenres(profileId);
        return ResponseEntity.ok(body);
    }
}
