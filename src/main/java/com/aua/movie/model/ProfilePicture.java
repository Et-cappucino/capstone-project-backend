package com.aua.movie.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile_picture")
public class ProfilePicture {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "image_file_name", nullable = false)
    private String name;

    @Column(name = "image_file_type", nullable = false)
    private String type;

    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime uploadedAt;

    @Lob
    @Column(name = "image_file_data", nullable = false)
    private byte[] imageData;

    @OneToOne
    @MapsId
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
