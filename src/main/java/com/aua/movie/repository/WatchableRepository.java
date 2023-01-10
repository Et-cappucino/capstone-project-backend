package com.aua.movie.repository;

import com.aua.movie.model.Watchable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchableRepository extends JpaRepository<Watchable, Long> {
}
