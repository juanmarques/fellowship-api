package com.fellowship.api.services;

import com.fellowship.api.controllers.exception.BadRequestException;
import com.fellowship.api.controllers.exception.ResourceNotFoundException;
import com.fellowship.api.domain.dtos.UserProfileDTO;
import com.fellowship.api.domain.entities.FellowshipUser;
import com.fellowship.api.domain.payload.AuthResponse;
import com.fellowship.api.domain.payload.LoginRequest;
import com.fellowship.api.domain.payload.SignUpRequest;
import com.fellowship.api.repositories.UserRepository;
import com.fellowship.api.security.authentication.TokenProvider;
import com.fellowship.api.security.authentication.model.UserPrincipal;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Created by Juan Marques on 21/11/2021
 */
@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;


    @Override
    public UserProfileDTO loadUserProfile(String userId) {

        FellowshipUser user = userOrNull(userId);

        if (user != null) {
            return UserProfileDTO.builder()
                    .userId(userId)
                    .about(user.getAbout())
                    .birthdayDate(user.getBirthdayDate())
                    .city(user.getCity())
                    .contact(user.getContact())
                    .name(user.getName())
                    .job(user.getJob())
                    .hobbies(user.getHobbies())
                    .neighbourhood(user.getNeighbourhood())
                    .profilePic(user.getProfilePic())
                    .relationship(user.getRelationship())
                    .build();
        }

        return UserProfileDTO.builder().build();
    }

    @Override
    public UserProfileDTO updateUserProfile(UserProfileDTO userProfile, UserPrincipal currentUser) {

        FellowshipUser user = userOrNull(currentUser.getId());

        if (user != null) {

            if (userProfile.getAbout() != null) {
                user.setAbout(userProfile.getAbout());
            }
            if (userProfile.getRelationship() != null) {
                user.setRelationship(userProfile.getRelationship());
            }
            if (userProfile.getJob() != null) {
                user.setJob(userProfile.getJob());
            }
            if (userProfile.getHobbies() != null) {
                user.setHobbies(userProfile.getHobbies());
            }
            if (userProfile.getCity() != null) {
                user.setCity(userProfile.getCity());
            }
            if (userProfile.getContact() != null) {
                user.setContact(userProfile.getContact());
            }
            if (userProfile.getBirthdayDate() != null) {
                user.setBirthdayDate(userProfile.getBirthdayDate());
            }
            if (userProfile.getNeighbourhood() != null) {
                user.setNeighbourhood(userProfile.getNeighbourhood());
            }
            return buildUserProfileDTO(this.userRepository.save(user));
        }

        throw new ResourceNotFoundException("Usuario", currentUser.getId(), "nao encontrado");
    }

    @Override
    public UserProfileDTO updateSettings(UserProfileDTO userProfile, UserPrincipal currentUser) {

        FellowshipUser user = userOrNull(currentUser.getId());

        if (user != null) {
            if (!ObjectUtils.isEmpty(userProfile.getName())) {
                user.setName(userProfile.getName());
            }
            if (!ObjectUtils.isEmpty(userProfile.getPostalCode())) {
                user.setPostalCode(userProfile.getPostalCode());
            }
            if (!ObjectUtils.isEmpty(userProfile.getNeighbourhood())) {
                user.setNeighbourhood(userProfile.getNeighbourhood());
            }

            if (!ObjectUtils.isEmpty(userProfile.getEmail())) {
                user.setEmail(userProfile.getEmail());
            }
            if (!ObjectUtils.isEmpty(userProfile.getPassword())) {
                user.setPassword(bCryptPasswordEncoder.encode(userProfile.getPassword()));
            }

           return buildUserProfileDTO(this.userRepository.save(user));
        }
        throw new ResourceNotFoundException("Usuario", currentUser.getId(), "nao encontrado");
    }

    @Override
    public void updateProfilePic(String profilePicUrl, UserPrincipal currentUser) {
        FellowshipUser user = userOrNull(currentUser.getId());

        if (user != null) {
            user.setProfilePic(profilePicUrl);
            this.userRepository.save(user);
        }
    }

    private FellowshipUser userOrNull(String userId) {
        return this.userRepository.findById(UUID.fromString(userId)).orElse(null);
    }

    @Override
    public AuthResponse registerUser(SignUpRequest signUpRequest) {

        if (existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Usuario ou senha incorreto");
        }

        FellowshipUser user = new FellowshipUser();

        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(signUpRequest.getPassword()));
        user.setBirthdayDate(signUpRequest.getBirthdayDate());
        user.setPostalCode(signUpRequest.getPostalCode());
        user.setNeighbourhood(signUpRequest.getNeighbourhood());

        return AuthResponse.builder()
                .user(buildUserProfileDTO(this.userRepository.save(user)))
                .token(createToken(signUpRequest.getEmail(), signUpRequest.getPassword()))
                .build();
    }

    @Override
    public AuthResponse doLogin(LoginRequest loginRequest) {
        Optional<FellowshipUser> optionalUser = this.userRepository.findByEmail(loginRequest.getEmail());

        if (optionalUser.isPresent()) {
            FellowshipUser user = optionalUser.get();
            return AuthResponse.builder()
                    .user(buildUserProfileDTO(user))
                    .token(createToken(loginRequest.getEmail(), loginRequest.getPassword()))
                    .build();
        }
        throw new BadRequestException("O email que você inseriu não está conectado a uma conta.");
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.findByEmail(email).isPresent();
    }

    private String createToken(String email, String password) {

        // Authenticate before create token
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.createToken(authentication);
    }

    private UserProfileDTO buildUserProfileDTO(FellowshipUser dbUser) {

        return UserProfileDTO.builder()
                .userId(dbUser.getId().toString())
                .about(dbUser.getAbout())
                .birthdayDate(dbUser.getBirthdayDate())
                .city(dbUser.getCity())
                .contact(dbUser.getContact())
                .name(dbUser.getName())
                .job(dbUser.getJob())
                .hobbies(dbUser.getHobbies())
                .neighbourhood(dbUser.getNeighbourhood())
                .profilePic(dbUser.getProfilePic())
                .relationship(dbUser.getRelationship())
                .postalCode(dbUser.getPostalCode())
                .email(dbUser.getEmail())
                .build();
    }
}
