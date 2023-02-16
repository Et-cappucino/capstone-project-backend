package com.aua.movie.controller;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.service.FilteredWatchablesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Filtered Watchables service rest API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/filter")
public class FilteredWatchablesController {

    private final FilteredWatchablesService filteredWatchablesService;

    @ApiOperation(value = "Get all Movies from all watchables with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/movies")
    public ResponseEntity<Page<WatchableDto>> getAllMovies(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findAllMovies(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all Series from all watchables with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/series")
    public ResponseEntity<Page<WatchableDto>> getAllSeries(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findAllSeries(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all the latest Watchables with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/latest")
    public ResponseEntity<Page<WatchableDto>> getLatestWatchables(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findLatest(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all the popular Watchables with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/popular")
    public ResponseEntity<Page<WatchableDto>> getPopularWatchables(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                   @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findPopular(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all the upcoming Watchables with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/upcoming")
    public ResponseEntity<Page<WatchableDto>> getUpcomingWatchables(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findUpcoming(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all the latest Movies with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/latest/movies")
    public ResponseEntity<Page<WatchableDto>> getLatestMovies(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                              @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findLatestMovies(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all the latest Series with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/latest/series")
    public ResponseEntity<Page<WatchableDto>> getLatestSeries(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                              @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findLatestSeries(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all the popular Movies with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/popular/movies")
    public ResponseEntity<Page<WatchableDto>> getPopularMovies(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findPopularMovies(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all the popular Series with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/popular/series")
    public ResponseEntity<Page<WatchableDto>> getPopularSeries(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findPopularSeries(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all the upcoming Movies with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/upcoming/movies")
    public ResponseEntity<Page<WatchableDto>> getUpcomingMovies(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findUpcomingMovies(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all the upcoming Series with pagination support", tags = "filtered-watchables-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/upcoming/series")
    public ResponseEntity<Page<WatchableDto>> getUpcomingSeries(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = filteredWatchablesService.findUpcomingSeries(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }
}
