package com.fellowship.api.domain.payload;

import com.fellowship.api.domain.dtos.UserProfileDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Juan Marques
 *
 */
@Getter
@Setter
@Builder
public class AuthResponse {
    
    private String token;
    private String tokenType = "Bearer";
    private UserProfileDTO user;
}
