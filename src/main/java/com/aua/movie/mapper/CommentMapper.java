package com.aua.movie.mapper;

import com.aua.movie.dto.CommentDto;
import com.aua.movie.model.Comment;
import com.aua.movie.model.Profile;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.repository.WatchableRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring")
public abstract class CommentMapper {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private WatchableRepository watchableRepository;

    public CommentDto commentToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .createdAt(comment.getCreatedAt())
                .text(comment.getText())
                .commenterId(comment.getCommenter().getId())
                .watchableId(comment.getWatchable().getId())
                .build();
    }

    public Comment commentDtoToComment(CommentDto commentDto) {
        Profile profile = profileRepository.findById(commentDto.getCommenterId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Watchable watchable = watchableRepository.findById(commentDto.getWatchableId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Comment comment = new Comment();
        comment.setCommenter(profile);
        comment.setWatchable(watchable);
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setCreatedAt(commentDto.getCreatedAt());
        return comment;
    }
}