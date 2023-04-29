package com.aua.movie.controller;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.service.RecommendationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.aua.movie.model.enums.RecommendationType.CONTENT_BASED;
import static com.aua.movie.model.enums.RecommendationType.GENRE_BASED;

@Api(value = "Recommendation service rest API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @ApiOperation(value = "Get recommended Watchables based on genres of the provided watchable with pagination support", tags = "recommendation-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/genre_based")
    public ResponseEntity<Page<WatchableDto>> recommendWatchablesBasedOnGenre(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                              @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                              @RequestParam(value = "watchableId") Long watchableId) {
        Page<WatchableDto> body = recommendationService.findAllRecommended(GENRE_BASED, watchableId, PageRequest.of(pageNumber,pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get recommended Watchables based on content of the provided watchable with pagination support", tags = "recommendation-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/content_based")
    public ResponseEntity<Page<WatchableDto>> recommendWatchablesBasedOnContent(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                                @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                                @RequestParam(value = "watchableId") Long watchableId) {
        Page<WatchableDto> body = recommendationService.findAllRecommended(CONTENT_BASED, watchableId, PageRequest.of(pageNumber,pageSize));
        return ResponseEntity.ok(body);
    }
}
