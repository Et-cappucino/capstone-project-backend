package com.aua.movie.repository;

import com.aua.movie.model.Watchable;
import com.aua.movie.model.enums.WatchableType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface WatchableRepository extends JpaRepository<Watchable, Long> {

    Page<Watchable> findAllByType(WatchableType type, Pageable pageRequest);

    @Query("SELECT w FROM Watchable w WHERE w.releaseDate > :cutoffDate AND w.releaseDate < :now")
    Page<Watchable> findAllLatest(@Param("cutoffDate") LocalDate cutoffDate,
                                  @Param("now") LocalDate now,
                                  Pageable pageRequest);

    @Query("SELECT w FROM Watchable w WHERE w.rating >= :minRating")
    Page<Watchable> findAllPopular(@Param("minRating") double minRating, Pageable pageRequest);

    @Query("SELECT w FROM Watchable w WHERE w.releaseDate > :now")
    Page<Watchable> findAllUpcoming(@Param("now") LocalDate now, Pageable pageRequest);

    @Query("SELECT w FROM Watchable w WHERE w.releaseDate > :cutoffDate AND w.releaseDate < :now AND w.type = :type")
    Page<Watchable> findLatest(@Param("cutoffDate") LocalDate cutoffDate,
                               @Param("now") LocalDate now,
                               @Param("type") WatchableType type,
                               Pageable pageRequest);

    @Query("SELECT w FROM Watchable w WHERE w.rating >= :minRating AND w.type = :type")
    Page<Watchable> findPopular(@Param("minRating") double minRating,
                                @Param("type") WatchableType type,
                                Pageable pageRequest);

    @Query("SELECT w FROM Watchable w WHERE w.releaseDate > :now AND w.type = :type")
    Page<Watchable> findUpcoming(@Param("now") LocalDate now,
                                 @Param("type") WatchableType type,
                                 Pageable pageRequest);

    Page<Watchable> findByNameStartingWithIgnoreCase(String name, Pageable pageRequest);
}
