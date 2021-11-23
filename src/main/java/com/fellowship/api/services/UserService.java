package com.fellowship.api.services;

import com.fellowship.api.domain.dtos.UserProfileDTO;
import com.fellowship.api.domain.payload.AuthResponse;
import com.fellowship.api.domain.payload.LoginRequest;
import com.fellowship.api.domain.payload.SignUpRequest;
import com.fellowship.api.security.authentication.model.UserPrincipal;

/**
 * @author Created by Juan Marques on 21/11/2021
 */

public interface UserService {
    UserProfileDTO loadUserProfile(String userId);
    UserProfileDTO updateUserProfile(UserProfileDTO userProfile, UserPrincipal currentUser);
    UserProfileDTO updateSettings(UserProfileDTO userProfile, UserPrincipal currentUser);
    void updateProfilePic(String profilePicUrl,UserPrincipal currentUser);
    AuthResponse doLogin(LoginRequest loginRequest);
    AuthResponse registerUser(SignUpRequest signUpRequest);
    boolean existsByEmail(String email);
}
