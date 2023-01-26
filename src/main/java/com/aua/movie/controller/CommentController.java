package com.aua.movie.controller;

import com.aua.movie.dto.CommentDto;
import com.aua.movie.service.CommentService;
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

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDto> postComment(@RequestBody @Valid CommentDto commentDto) {
        CommentDto body = commentService.postComment(commentDto);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{profileId}/{watchableId}")
    public ResponseEntity<List<CommentDto>> getProfileAllWatchableComments(@PathVariable Long profileId,
                                                                           @PathVariable Long watchableId) {
        List<CommentDto> body = commentService.findAllComments(profileId, watchableId);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable Long id) {
        CommentDto body = commentService.getComment(id);
        return ResponseEntity.ok(body);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody @Valid CommentDto commentDto,
                                                    @PathVariable Long id) {
        CommentDto body = commentService.editComment(commentDto, id);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeComment(@PathVariable Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok().build();
    }
}