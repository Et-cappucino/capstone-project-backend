package com.aua.movie.controller;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.model.enums.Genre;
import com.aua.movie.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Search Watchables service rest API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @ApiOperation(value = "Get watchables by search with pagination and sorting support", tags = "search-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping
    public ResponseEntity<Page<WatchableDto>> searchWatchables(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                               @RequestParam(name = "sort", defaultValue = "rating") String sort,
                                                               @RequestParam(value = "query", required = false) String query) {
        Page<WatchableDto> body = searchService.searchWatchable(query, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get watchables by search with pagination and sorting support when Logged In", tags = "search-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/userSearch")
    public ResponseEntity<Page<WatchableDto>> userSearchWatchables(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                               @RequestParam(name = "sort", defaultValue = "rating") String sort,
                                                               @RequestParam(value = "query", required = false) String query,
                                                               @RequestParam(value = "email") String profileEmail) {
        Page<WatchableDto> body = searchService.userSearchWatchable(query, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)), profileEmail);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get watchables by Genre search with pagination and sorting support", tags = "search-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/genre")
    public ResponseEntity<Page<WatchableDto>> searchWatchablesByGenre(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                      @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                      @RequestParam(name = "sort", defaultValue = "rating") String sort,
                                                                      @RequestParam(value = "genre", required = false) Genre genre) {
        Page<WatchableDto> body = searchService.searchWatchableByGenre(genre, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get watchables by Release Year search with pagination and sorting support", tags = "search-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/releaseYear")
    public ResponseEntity<Page<WatchableDto>> searchWatchablesByReleaseYear(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                            @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                            @RequestParam(name = "sort", defaultValue = "releaseDate") String sort,
                                                                            @RequestParam(value = "releaseYear", required = false) Integer releaseYear) {
        Page<WatchableDto> body = searchService.searchWatchableByReleaseYear(releaseYear, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get movies by Genre search with pagination and sorting support", tags = "search-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/movie/genre")
    public ResponseEntity<Page<WatchableDto>> searchMoviesByGenre(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(name = "sort", defaultValue = "rating") String sort,
                                                                  @RequestParam(value = "genre", required = false) Genre genre) {
        Page<WatchableDto> body = searchService.searchMovieByGenre(genre, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get movies by Release Year search with pagination and sorting support", tags = "search-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/movie/releaseYear")
    public ResponseEntity<Page<WatchableDto>> searchMoviesByReleaseYear(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                        @RequestParam(name = "sort", defaultValue = "releaseDate") String sort,
                                                                        @RequestParam(value = "releaseYear", required = false) Integer releaseYear) {
        Page<WatchableDto> body = searchService.searchMovieReleaseYear(releaseYear, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get series by Genre search with pagination and sorting support", tags = "search-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/series/genre")
    public ResponseEntity<Page<WatchableDto>> searchSeriesByGenre(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(name = "sort", defaultValue = "rating") String sort,
                                                                  @RequestParam(value = "genre", required = false) Genre genre) {
        Page<WatchableDto> body = searchService.searchSeriesByGenre(genre, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get series by Release Year search with pagination and sorting support", tags = "search-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/series/releaseYear")
    public ResponseEntity<Page<WatchableDto>> searchSeriesByReleaseYear(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                        @RequestParam(name = "sort", defaultValue = "releaseDate") String sort,
                                                                        @RequestParam(value = "releaseYear", required = false) Integer releaseYear) {
        Page<WatchableDto> body = searchService.searchSeriesByReleaseYear(releaseYear, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
        return ResponseEntity.ok(body);
    }
}
