package com.aua.movie.service;

import com.aua.movie.dto.ProfilePictureDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProfilePictureService {

    ProfilePictureDto getProfilePicture(Long id);

    ProfilePictureDto uploadProfilePicture(MultipartFile imageFile, Long id) throws IOException;
}
