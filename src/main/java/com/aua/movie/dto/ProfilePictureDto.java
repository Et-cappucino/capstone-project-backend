package com.aua.movie.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represents an info which is required to create a ProfilePicture")
public class ProfilePictureDto {

    @ApiModelProperty(value = "profileId or pictureId")
    private Long id;

    @ApiModelProperty(value = "Name of the profile picture")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "Type of the profile picture")
    @NotBlank
    private String type;

    @ApiModelProperty(value = "Profile picture's upload time")
    private LocalDateTime uploadedAt;

    @ApiModelProperty(value = "Profile picture of the user in bytes")
    private byte[] imageData;
}
