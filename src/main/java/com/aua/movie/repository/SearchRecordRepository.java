package com.aua.movie.repository;

import com.aua.movie.model.SearchRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchRecordRepository extends JpaRepository<SearchRecord, Long> {
}
