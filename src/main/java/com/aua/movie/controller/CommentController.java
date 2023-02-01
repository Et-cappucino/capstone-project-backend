package com.aua.movie.controller;

import com.aua.movie.dto.CommentDto;
import com.aua.movie.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Comment service rest API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @ApiOperation(value = "Post a Comment by providing a Comment body with commenterID and watchableID", response = CommentDto.class, tags = "comment-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping
    public ResponseEntity<CommentDto> postComment(@RequestBody @Valid CommentDto commentDto) {
        CommentDto body = commentService.postComment(commentDto);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all Comments from a particular Profile under a particular Watchable by IDs", tags = "comment-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/{profileId}/{watchableId}")
    public ResponseEntity<List<CommentDto>> getProfileAllWatchableComments(@PathVariable Long profileId,
                                                                           @PathVariable Long watchableId) {
        List<CommentDto> body = commentService.findAllComments(profileId, watchableId);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all Comments under a particular Watchable by ID", tags = "comment-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/{watchableId}")
    public ResponseEntity<List<CommentDto>> getWatchableAllComments(@PathVariable Long watchableId) {
        List<CommentDto> body = commentService.findWatchableAllComments(watchableId);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get a single Comment by ID", tags = "comment-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/comment-{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id) {
        CommentDto body = commentService.getComment(id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Edit a Comment by ID", tags = "comment-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody @Valid CommentDto commentDto,
                                                    @PathVariable Long id) {
        CommentDto body = commentService.editComment(commentDto, id);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Delete a Comment by ID", tags = "comment-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}