package com.aua.movie.service.impl;

import com.aua.movie.dto.CommentDto;
import com.aua.movie.mapper.CommentMapper;
import com.aua.movie.model.Comment;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.CommentRepository;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final WatchableRepository watchableRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Override
    public List<CommentDto> findAllComments(Long profileId, Long watchableId) {
        List<Comment> allComments = commentRepository.findAll();
        return allComments.stream()
                .filter(comment -> comment.getCommenter().getId().equals(profileId)
                        && comment.getWatchable().getId().equals(watchableId))
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<CommentDto> findWatchableAllComments(Long watchableId) {
        Watchable watchable = watchableRepository.findById(watchableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return watchable.getComments()
                .stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<CommentDto> findProfileAllComments(Long profileId, Pageable pageRequest) {
        return commentRepository.findAllByCommenterId(profileId, pageRequest)
                .map(commentMapper::commentToCommentDto);
    }

    @Override
    public CommentDto getComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return commentMapper.commentToCommentDto(comment);
    }

    @Override
    public CommentDto postComment(CommentDto commentDto) {
        Comment comment = commentMapper.commentDtoToComment(commentDto);
        commentRepository.save(comment);
        return commentMapper.commentToCommentDto(comment);
    }

    @Override
    public CommentDto editComment(CommentDto commentDto, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment updatedComment = update(comment, commentMapper.commentDtoToComment(commentDto));

        commentRepository.save(updatedComment);
        return commentMapper.commentToCommentDto(updatedComment);
    }

    @Override
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    private Comment update(Comment current, Comment updated) {
        current.setText(updated.getText());
        current.setCreatedAt(updated.getCreatedAt());
        current.setCommenter(updated.getCommenter());
        current.setWatchable(updated.getWatchable());
        return current;
    }
}