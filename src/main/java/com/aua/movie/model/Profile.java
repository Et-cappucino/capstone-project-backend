package com.aua.movie.model;

import com.aua.movie.model.enums.Genre;
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
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "is_admin")
    private boolean isAdmin;

    @Column(name = "is_enabled")
    private boolean enabled = false;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private ProfilePicture profilePicture;

    @OneToOne(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    @PrimaryKeyJoinColumn
    private EmailConfirmationToken emailConfirmationToken;

    @ManyToMany
    @JoinTable(
            name = "watchlist",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "watchable_id"))
    private List<Watchable> watchlist;

    @ManyToMany
    @JoinTable(
            name = "favorites",
            joinColumns = @JoinColumn(name = "profile_id"),
            inverseJoinColumns = @JoinColumn(name = "watchable_id"))
    private List<Watchable> favorites;

    @OneToMany(mappedBy = "commenter", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SearchRecord> searches;

    @ElementCollection(targetClass = Genre.class)
    @JoinTable(name = "favorite_genres", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "genre", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Genre> favoriteGenres;
}
