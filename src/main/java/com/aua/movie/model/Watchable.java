package com.aua.movie.model;

import com.aua.movie.model.enums.Genre;
import com.aua.movie.model.enums.WatchableType;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "watchable")
public class Watchable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private WatchableType type;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "trailer_link")
    private String trailerLink;

    @Column(name = "rating", nullable = false)
    private double rating;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "poster_path")
    private String posterPath;

    @ElementCollection(targetClass = String.class)
    @JoinTable(name = "Backdrops", joinColumns = @JoinColumn(name = "watchable_id"))
    @Column(name = "backdrop_path", nullable = false)
    private List<String> backdropPaths;

    @ElementCollection(targetClass = Genre.class)
    @JoinTable(name = "Genres", joinColumns = @JoinColumn(name = "watchable_id"))
    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "cast",
            joinColumns = @JoinColumn(name = "watchable_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<Actor> cast;

    @OneToMany(mappedBy = "watchable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}
