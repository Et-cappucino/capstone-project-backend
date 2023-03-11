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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Set;

@Api(value = "Favorite genres service rest API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class FavoriteGenresController {

    private final FavoriteGenresService favoriteGenresService;

    @ApiOperation(value = "Add Genre(s) to the Profile's Favorite Genres", tags = "favorite-genres-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping("/{profileId}")
    public ResponseEntity<Void> addToFavoriteGenres(@RequestBody @Valid Set<Genre> genres, @PathVariable Long profileId) {
        favoriteGenresService.addToFavoriteGenres(genres, profileId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Remove Genre(s) from the Profile's Favorite Genres", tags = "favorite-genres-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> removeFromFavoriteGenres(@RequestParam(value = "genre") Genre genre, @PathVariable Long profileId) {
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
