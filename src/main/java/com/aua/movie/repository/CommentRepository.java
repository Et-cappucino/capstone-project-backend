package com.aua.movie.repository;

import com.aua.movie.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAllByCommenterIdAndWatchableId(Long profileId, Long watchableId, Pageable pageRequest);

    Page<Comment> findAllByWatchableId(Long watchableId, Pageable pageRequest);

    Page<Comment> findAllByCommenterId(Long profileId, Pageable pageRequest);
}