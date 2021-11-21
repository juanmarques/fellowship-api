package com.fellowship.api.controllers;

import com.fellowship.api.controllers.exception.BadRequestException;
import com.fellowship.api.domain.payload.LoginRequest;
import com.fellowship.api.domain.payload.SignUpRequest;
import com.fellowship.api.security.authentication.CurrentUser;
import com.fellowship.api.security.authentication.model.UserPrincipal;
import com.fellowship.api.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author Juan Marques
 */

@RestController
@AllArgsConstructor
@RequestMapping("auth")
@Log4j2
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok()
                .body(userService.doLogin(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {

        if (userService.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }
        return ResponseEntity.ok()
                .body(userService.registerUser(signUpRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@CurrentUser UserPrincipal userPrincipal) {
        log.info("Principal {} " , userPrincipal);
        SecurityContextHolder.getContext().setAuthentication(null);
        return ResponseEntity.ok("user logged out");
    }

}
