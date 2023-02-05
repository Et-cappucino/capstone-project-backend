package com.aua.movie.controller;

import com.aua.movie.dto.ActorDto;
import com.aua.movie.service.CastService;
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

import java.util.List;

@Api(value = "Cast service rest API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cast")
public class CastController {

    private final CastService castService;

    @ApiOperation(value = "Add an Actor to the Watchable's Cast by IDs", tags = "cast-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping("/{watchableId}/{actorId}")
    public ResponseEntity<Void> addToCast(@PathVariable Long actorId, @PathVariable Long watchableId) {
        castService.addToCast(actorId, watchableId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Remove an Actor from the Watchable's Cast by IDs", tags = "cast-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping("/{watchableId}/{actorId}")
    public ResponseEntity<Void> removeFromCast(@PathVariable Long actorId, @PathVariable Long watchableId) {
        castService.removeFromCast(actorId, watchableId);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Get a Watchable's Cast by ID", tags = "cast-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/{watchableId}")
    public ResponseEntity<List<ActorDto>> getWatchableCast(@PathVariable Long watchableId) {
        List<ActorDto> body = castService.getWatchableCast(watchableId);
        return ResponseEntity.ok(body);
    }
}
