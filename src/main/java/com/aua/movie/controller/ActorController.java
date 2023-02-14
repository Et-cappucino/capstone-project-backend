package com.aua.movie.controller;

import com.aua.movie.dto.ActorDto;
import com.aua.movie.service.ActorService;
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

@Api(value = "Actor service rest API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/actors")
public class ActorController {

    private final ActorService actorService;

    @ApiOperation(value = "Get all Actors with pagination support", tags = "actor-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping
    public ResponseEntity<Page<ActorDto>> getAllActors(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                       @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        Page<ActorDto> body = actorService.findAll(PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get a single Actor by ID", tags = "actor-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/{id}")
    public ResponseEntity<ActorDto> getActor(@PathVariable Long id) {
        ActorDto body = actorService.getActor(id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Create and register a new Actor", response = ActorDto.class, tags = "actor-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ActorDto> registerActor(@RequestBody @Valid ActorDto actorDto) {
        ActorDto body = actorService.registerActor(actorDto);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Update an Actor by ID with a new Actor body", tags = "actor-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ActorDto> updateActor(@RequestBody @Valid ActorDto actorDto, @PathVariable Long id) {
        ActorDto body = actorService.updateActor(actorDto, id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Delete an Actor by ID", tags = "actor-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> removeActor(@PathVariable Long id) {
        actorService.deleteActor(id);
        return ResponseEntity.ok().build();
    }
}
