package com.fellowship.api.domain.payload;

import lombok.Data;

/**
 * @author Juan Marques
 */
@Data
public class SignUpRequest {


    private String name;
    private String email;
    private String password;
    private String postalCode;
    private String birthdayDate;
    private String neighbourhood;
}
