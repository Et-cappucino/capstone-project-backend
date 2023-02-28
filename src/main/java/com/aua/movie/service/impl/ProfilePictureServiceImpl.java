package com.aua.movie.service.impl;

import com.aua.movie.dto.ProfilePictureDto;
import com.aua.movie.mapper.ProfilePictureMapper;
import com.aua.movie.model.Profile;
import com.aua.movie.model.ProfilePicture;
import com.aua.movie.repository.ProfilePictureRepository;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.service.ProfilePictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@RequiredArgsConstructor
public class ProfilePictureServiceImpl implements ProfilePictureService {

    private final ProfileRepository profileRepository;
    private final ProfilePictureRepository profilePictureRepository;
    private final ProfilePictureMapper profilePictureMapper;

    @Override
    public ProfilePictureDto getProfilePicture(Long id) {
        ProfilePicture profilePicture = profilePictureRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        profilePicture.setImageData(decompressImage(profilePicture.getImageData()));
        return profilePictureMapper.profilePictureToProfilePictureDto(profilePicture);
    }

    @Override
    public ProfilePictureDto addProfilePicture(MultipartFile imageFile, Long profileId) throws IOException {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        ProfilePicture profilePicture = ProfilePicture.builder()
                .name(imageFile.getOriginalFilename()
                        .substring(0, imageFile.getOriginalFilename().lastIndexOf(".")))
                .type(imageFile.getContentType())
                .imageData(compressImage(imageFile.getBytes()))
                .uploadedAt(LocalDateTime.now())
                .profile(profile)
                .build();

        profilePictureRepository.save(profilePicture);
        return profilePictureMapper.profilePictureToProfilePictureDto(profilePicture);
    }

    @Override
    public ProfilePictureDto updateProfilePicture(MultipartFile imageFile, Long id) throws IOException {
        ProfilePicture profilePicture = profilePictureRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ProfilePicture updatedProfilePicture = update(profilePicture, imageFile);

        profilePictureRepository.save(updatedProfilePicture);
        return profilePictureMapper.profilePictureToProfilePictureDto(updatedProfilePicture);
    }

    private ProfilePicture update(ProfilePicture current, MultipartFile updatedImageFile) throws IOException {
        current.setName(updatedImageFile.getOriginalFilename()
                .substring(0, updatedImageFile.getOriginalFilename().lastIndexOf(".")));
        current.setType(updatedImageFile.getContentType());
        current.setImageData(compressImage(updatedImageFile.getBytes()));
        current.setUploadedAt(LocalDateTime.now());
        return current;
    }

    private byte[] compressImage(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }
        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }

    private byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
}
