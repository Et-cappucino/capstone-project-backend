package com.aua.movie.service;

import com.aua.movie.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CommentService {

    List<CommentDto> findAllComments(Long profileId, Long watchableId);

    CommentDto getComment(Long commentId);

    CommentDto postComment(CommentDto commentDto);

    CommentDto editComment(CommentDto commentDto, Long commentId);

    void deleteComment(Long commentId);

    List<CommentDto> findWatchableAllComments(Long watchableId);

    Page<CommentDto> findProfileAllComments(Long profileId, Pageable pageRequest);
}