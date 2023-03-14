package com.aua.movie.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "rate")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "question_one_value")
    private Double questionOneValue;

    @Column(name = "question_two_value")
    private Double questionTwoValue;

    @Column(name = "question_three_value")
    private Double questionThreeValue;

    @Column(name = "rated_at", nullable = false)
    private LocalDateTime ratedAt;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "watchable_id", nullable = false)
    private Watchable watchable;
}
