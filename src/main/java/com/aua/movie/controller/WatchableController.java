package com.aua.movie.controller;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.service.WatchableService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

@Api(value = "Watchable service rest API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/watchables")
public class WatchableController {

    private final WatchableService watchableService;

    @ApiOperation(value = "Get all Watchables with pagination support", tags = "watchable-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping
    public ResponseEntity<Page<WatchableDto>> getAllWatchables(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<WatchableDto> body = watchableService.findAll(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get a single Watchable by ID", tags = "watchable-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/{id}")
    public ResponseEntity<WatchableDto> getWatchable(@PathVariable Long id) {
        WatchableDto body = watchableService.getWatchable(id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Create and register a new Watchable", response = WatchableDto.class, tags = "watchable-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<WatchableDto> registerWatchable(@RequestBody @Valid WatchableDto watchableDto) {
        WatchableDto body = watchableService.registerWatchable(watchableDto);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Update a Watchable by ID with a new Watchable body", tags = "watchable-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<WatchableDto> updateWatchable(@RequestBody @Valid WatchableDto watchableDto, @PathVariable Long id) {
        WatchableDto body = watchableService.updateWatchable(watchableDto, id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Delete a Watchable by ID", tags = "watchable-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> removeWatchable(@PathVariable Long id) {
        watchableService.deleteWatchable(id);
        return ResponseEntity.ok().build();
    }
}
