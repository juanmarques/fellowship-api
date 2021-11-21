package com.fellowship.api.controllers;

import com.fellowship.api.domain.dtos.UserProfileDTO;
import com.fellowship.api.security.authentication.CurrentUser;
import com.fellowship.api.security.authentication.model.UserPrincipal;
import com.fellowship.api.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by Juan Marques on 21/11/2021
 */
@RestController
@AllArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @PutMapping("/update_profile")
    private ResponseEntity<?> updateProfile(@RequestBody UserProfileDTO userProfile,@CurrentUser UserPrincipal currentUser) {
        return ResponseEntity.ok(userService.updateUserProfile(userProfile,currentUser));
    }

    @GetMapping("/{user_id}")
    private ResponseEntity<?> loadUserProfile(@PathVariable String user_id) {
        return ResponseEntity.ok(userService.loadUserProfile(user_id));
    }
}
