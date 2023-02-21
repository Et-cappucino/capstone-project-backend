package com.aua.movie.service;

import com.aua.movie.dto.CommentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {

    Page<CommentDto> findAllComments(Long profileId, Long watchableId, Pageable pageRequest);

    CommentDto getComment(Long commentId);

    CommentDto postComment(CommentDto commentDto);

    CommentDto editComment(CommentDto commentDto, Long commentId);

    void deleteComment(Long commentId);

    Page<CommentDto> findWatchableAllComments(Long watchableId, Pageable pageRequest);

    Page<CommentDto> findProfileAllComments(Long profileId, Pageable pageRequest);
}