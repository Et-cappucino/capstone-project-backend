package com.aua.movie.controller;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.service.WatchlistService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Watchlist service rest API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/watchlist")
public class WatchlistController {

    private final WatchlistService watchlistService;

    @ApiOperation(value = "Add a Watchable to the Profile's Watchlist by IDs", tags = "watchlist-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping("/{profileId}/{watchableId}")
    public ResponseEntity<Void> addToWatchlist(@PathVariable Long watchableId, @PathVariable Long profileId) {
        watchlistService.addToWatchlist(watchableId, profileId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Remove a Watchable from the Profile's Watchlist by IDs", tags = "watchlist-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping("/{profileId}/{watchableId}")
    public ResponseEntity<Void> removeFromWatchlist(@PathVariable Long watchableId, @PathVariable Long profileId) {
        watchlistService.removeFromWatchlist(watchableId, profileId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Get a Profile's Watchlist by ID with pagination support", tags = "watchlist-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/{profileId}")
    public ResponseEntity<Page<WatchableDto>> getProfileWatchlist(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                  @PathVariable Long profileId) {
        Page<WatchableDto> body = watchlistService.getProfileWatchlist(profileId, PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }
}
