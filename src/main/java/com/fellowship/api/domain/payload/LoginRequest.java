package com.fellowship.api.domain.payload;

import lombok.Data;

import javax.validation.constraints.Email;

/**
 * @author Juan Marques
 *
 */
@Data
public class LoginRequest {

    @Email
    private String email;
    private String password;  
}
