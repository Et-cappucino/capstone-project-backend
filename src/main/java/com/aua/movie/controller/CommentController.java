package com.aua.movie.controller;

import com.aua.movie.dto.CommentDto;
import com.aua.movie.service.CommentService;
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

    @ApiOperation(value = "Get all Comments from a particular Profile under a particular Watchable by IDs with pagination and sorting support", tags = "comment-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/profile-{profileId}/watchable-{watchableId}")
    public ResponseEntity<Page<CommentDto>> getProfileAllWatchableComments(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                           @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                           @RequestParam(name = "sort", defaultValue = "createdAt") String sort,
                                                                           @PathVariable Long profileId,
                                                                           @PathVariable Long watchableId) {
        Page<CommentDto> body = commentService.findAllComments(profileId, watchableId, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all Comments under a particular Watchable by ID with pagination and sorting support", tags = "comment-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/watchable-{watchableId}")
    public ResponseEntity<Page<CommentDto>> getWatchableAllComments(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                    @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                    @RequestParam(name = "sort", defaultValue = "createdAt") String sort,
                                                                    @PathVariable Long watchableId) {
        Page<CommentDto> body = commentService.findWatchableAllComments(watchableId,PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all Comments of a particular Profile by ID with pagination and sorting support", tags = "comment-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/profile-{profileId}")
    public ResponseEntity<Page<CommentDto>> getProfileAllComments(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(name = "sort", defaultValue = "createdAt") String sort,
                                                                  @PathVariable Long profileId) {
        Page<CommentDto> body = commentService.findProfileAllComments(profileId, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
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