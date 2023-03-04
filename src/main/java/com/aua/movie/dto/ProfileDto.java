package com.aua.movie.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represents an info which is required to create a Profile")
public class ProfileDto {

    @ApiModelProperty(value = "profileId")
    private Long id;

    @ApiModelProperty(value = "First Name of the user")
    @NotBlank
    private String firstName;

    @ApiModelProperty(value = "Last Name of the user")
    @NotBlank
    private String lastName;

    @ApiModelProperty(value = "Email of the user")
    @Email
    @NotBlank
    private String email;

    @ApiModelProperty(value = "Password of the user")
    @NotNull
    private String password;

    @ApiModelProperty(value = "Is the user an admin or not")
    private boolean isAdmin;

    @ApiModelProperty(value = "Is the user account enabled or not")
    private boolean enabled;
}
