package com.aua.movie.service;

import com.aua.movie.dto.CommentDto;

import java.util.List;

public interface CommentService {

    List<CommentDto> findAllComments(Long profileId, Long watchableId);

    CommentDto getComment(Long commentId);

    CommentDto postComment(CommentDto commentDto);

    CommentDto editComment(CommentDto commentDto, Long commentId);

    void deleteComment(Long commentId);
}