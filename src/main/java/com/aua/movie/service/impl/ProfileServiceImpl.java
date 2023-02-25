package com.aua.movie.service.impl;

import com.aua.movie.dto.ProfileDto;
import com.aua.movie.mapper.ProfileMapper;
import com.aua.movie.model.EmailConfirmationToken;
import com.aua.movie.model.Profile;
import com.aua.movie.model.enums.Role;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.service.EmailConfirmationTokenService;
import com.aua.movie.service.MailSenderService;
import com.aua.movie.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static com.aua.movie.model.enums.Role.ADMIN;
import static com.aua.movie.model.enums.Role.USER;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService, UserDetailsService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;
    private final PasswordEncoder passwordEncoder;
    private final EmailConfirmationTokenService emailConfirmationTokenService;
    private final MailSenderService emailSenderService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Profile user = profileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Role role = user.isAdmin() ? ADMIN : USER;
        return new User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(role.name()))
        );
    }

    @Override
    public Page<ProfileDto> findAll(Pageable pageRequest) {
        return profileRepository.findAll(pageRequest)
                .map(profileMapper::profileToProfileDto);
    }

    @Override
    public ProfileDto getProfile(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return profileMapper.profileToProfileDto(profile);
    }

    @Override
    public ProfileDto registerProfile(ProfileDto profileDto) {
        Profile profile = profileMapper.profileDtoToProfile(profileDto);
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profileRepository.save(profile);

        EmailConfirmationToken confirmationToken = emailConfirmationTokenService.generateToken(profile);
        emailConfirmationTokenService.saveEmailConfirmationToken(confirmationToken);

        String mail = emailSenderService.buildMail(profile.getFirstName(), confirmationToken.getToken());
        emailSenderService.send(profile.getEmail(), mail);

        return profileMapper.profileToProfileDto(profile);
    }

    @Override
    public ProfileDto updateProfile(ProfileDto profileDto, Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Profile updatedProfile = update(profile, profileMapper.profileDtoToProfile(profileDto));

        profileRepository.save(updatedProfile);
        return profileMapper.profileToProfileDto(updatedProfile);
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile getProfileByEmail(String email) {
        return profileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Profile update(Profile current, Profile updated) {
        current.setFirstName(updated.getFirstName());
        current.setLastName(updated.getLastName());
        current.setEmail(updated.getEmail());
        current.setPassword(updated.getPassword());
        current.setAdmin(updated.isAdmin());
        current.setEnabled(updated.isEnabled());
        current.setWatchlist(updated.getWatchlist());
        current.setFavorites(updated.getFavorites());
        return current;
    }
}
