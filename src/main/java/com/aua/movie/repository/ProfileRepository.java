package com.aua.movie.repository;

import com.aua.movie.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile,Long> {

    Optional<Profile> findByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Profile p " +
            "SET p.enabled = TRUE " +
            "WHERE p.email = ?1")
    void enableProfile(String email);
}
